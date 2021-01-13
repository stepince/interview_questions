import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

/*
     Interview question to convert a file from one format to another
     xml -> csv
     csv -> xml

    Add jaxb-api lib in IDE
    FIXME: should add a maven pom.xml to project
 */

/**
 *   New File types will have to implement this interface
 */

interface SchoolDocumentParser {
    DocumentConverter.SchoolDocument parse(Reader rdr );
}

/**
 *  SchoolDocumentFormatter Interface
 *  New file types will have implement this interface
 */
interface SchoolDocumentFormatter {
    void format(OutputStream os, DocumentConverter.School school);
}

public class DocumentConverter {

    final static boolean XML_PRETTY_PRINT = true;
    final static String XML_HDR = "<?xml";
    final static String CSV_CLASSROOM_ID = "classroom id";
    final static String CSV_SPLIT_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    final static int DETECT_LINE_LEN = 1000;

    enum FileType {
        XML, CSV, UNKNOWN
    }

    final static Map<FileType,String> FILE_TYPE_DETECT_MAP = Map.of(
            FileType.XML,XML_HDR,
            FileType.CSV,CSV_CLASSROOM_ID
    );

    final static Map<FileType,Class<? extends SchoolDocumentFormatter>> FILE_TYPE_FORMATTER_MAP = Map.of(
            FileType.XML, XMLSchoolDocumentFormatterImpl.class,
            FileType.CSV, CSVSchoolDocumentFormatterImpl.class
    );

    final static Map<FileType,Class<? extends SchoolDocumentParser>> FILE_TYPE_PARSER_MAP = Map.of(
            FileType.XML,XMLSchoolDocumentParserImpl.class,
            FileType.CSV,CSVSchoolDocumentParserImpl.class
    );

    enum CSV_FIELDS {
        classroom_name,
        teacher_1_id,
        teacher_1_last_name,
        teacher_1_first_name,
        teacher_2_id,
        teacher_2_last_name,
        teacher_2_first_name,
        student_id,
        student_first_name,
        student_last_name,
        student_grade,
    }

    static boolean isBlank(String str ){
        return str == null || str.trim().length() == 0;
    }

    static class ParseException extends RuntimeException {
        private static final long serialVersionUID = 1;
        Exception cause;
        public ParseException(Exception ex ){
            this.cause = ex;
        }
    }

    static class FormatterException extends RuntimeException {
        private static final long serialVersionUID = 1;
        Exception cause;
        public FormatterException(Exception ex ){
            this.cause = ex;
        }
    }

    static class DocumentException extends RuntimeException {
        private static final long serialVersionUID = 1;
        Exception cause;

        public DocumentException(Exception ex ){
            this.cause = ex;
        }
    }

    @XmlRootElement(name = "school")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class School implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        Integer id;

        @XmlElement(name = "grade")
        List<Grade> grades = new ArrayList<>();
        public Integer getId(){
            return id;
        }
        public void setId(Integer id){
            this.id=id;
        }
        public List<Grade> getGrades(){
            return this.grades;
        }
    }

    @XmlRootElement(name = "grade")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Grade implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        Integer id;

        @XmlElement(name = "classroom")
        List<Classroom> classrooms = new ArrayList<>();

        public Grade(Integer id){
            this.id=id;
        }
        public Integer getId(){
            return id;
        }
        public void setId(Integer id){
            this.id=id;
        }
        public List<Classroom> getClassrooms(){
            return this.classrooms;
        }
    }

    @XmlRootElement(name = "classroom")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Classroom implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        Integer id;
        @XmlAttribute
        String name;

        @XmlElement(name = "student")
        List<Student> students = new ArrayList<>();

        @XmlElement(name = "teacher")
        List<Teacher> teachers = new ArrayList<>();

        public Classroom(Integer id){
            this.id=id;
        }
        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name=name;
        }
        public Integer getId(){
            return id;
        }
        public void setId(Integer id){
            this.id=id;
        }
        public List<Teacher> getTeachers(){
            return this.teachers;
        }
        public List<Student> getStudents(){
            return this.students;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    static abstract class Person {
        @XmlAttribute
        Integer id;
        @XmlAttribute(name = "last_name")
        String lastName;
        @XmlAttribute(name = "first_name")
        String firstName;
        public Integer getId(){
            return id;
        }
        public void setId(Integer id){
            this.id=id;
        }
        public String getFirstName(){
            return this.firstName;
        }
        public void setFirstName(String firstName){
            this.firstName = firstName;
        }
        public String getLastName(){
            return this.lastName;
        }
        public void setLastName(String lastName){
            this.lastName = lastName;
        }
    }

    @XmlRootElement(name = "student")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Student extends Person implements Serializable {
        private static final long serialVersionUID = 1L;

        public Student(Integer id){
            this.id=id;
        }
    }

    @XmlRootElement(name = "teacher")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Teacher extends Person implements Serializable {
        private static final long serialVersionUID = 1L;

        public Teacher(Integer id){
            this.id=id;
        }
    }

    /**
       Implementation of XML Document Parser
    */
    static class XMLSchoolDocumentParserImpl implements SchoolDocumentParser {

        public SchoolDocument parse( Reader rdr ){
            try {
                SchoolDocument doc =  new SchoolDocument();
                doc.setFileType(FileType.XML);
                JAXBContext jc = JAXBContext.newInstance( School.class );
                Unmarshaller u = jc.createUnmarshaller();
                School school = (School)u.unmarshal( rdr );
                doc.setSchool(school);
                return doc;
            }
            catch ( Exception ex ){
                throw new ParseException(ex);
            }
        }
    }

    /**
     *  Implementation of CSV Document Parser
    */
    static class CSVSchoolDocumentParserImpl implements SchoolDocumentParser {
        Map<String,Integer> getHeader(String line){
            Map<String,Integer> hdr = new HashMap<>();
            String[] fields = line.split(CSV_SPLIT_REGEX);
            for ( int i = 1; i < fields.length;++i ){
                hdr.put(fields[i].trim(),i);
            }
            return hdr;
        }

        List<String> getRecordFromLine(String line){
            List<String> record = new ArrayList<>();
            for ( String val: line.split(CSV_SPLIT_REGEX) ){
                record.add(val.trim());
            }
            return record;
        }

        String getRecordValue( List<String> record, Map<String,Integer> hdr, CSV_FIELDS field ) {
            return record.get(hdr.get( field.name() ));
        }

        Integer getRecordValueAsInteger( List<String> record, Map<String,Integer> hdr, CSV_FIELDS field ) {
            String val = record.get(hdr.get( field.name() ));
            return isBlank(val) ? null : Integer.valueOf(val);
        }

        public SchoolDocument parse( Reader rdr ){
            try {
                final Map<Integer,Classroom> classroomMap = new HashMap<>();
                final Map<Integer,Grade> gradeMap = new HashMap<>();

                SchoolDocument doc = new SchoolDocument();
                doc.setFileType(FileType.CSV);
                School school = new School();

                doc.setSchool(school);
                Scanner scanner = new Scanner(rdr);
                if ( !scanner.hasNextLine() ) throw new Exception("Invalid CSV");

                Map<String,Integer> hdr = getHeader(scanner.nextLine());
                while (scanner.hasNextLine()) {
                    List<String> record = getRecordFromLine(scanner.nextLine());

                    //////////////////////////////////
                    // create grade
                    //////////////////////////////////

                    Integer gradeId = Integer.valueOf( getRecordValue( record, hdr, CSV_FIELDS.student_grade));
                    final Grade grade = gradeMap.computeIfAbsent(gradeId, key-> {
                        Grade myGrade = new Grade(gradeId);
                        school.getGrades().add(myGrade);
                        return myGrade;
                    });

                    //////////////////////////////////
                    // create classroom
                    //////////////////////////////////

                    // classroomId is always at index 0
                    Integer classroomId = Integer.valueOf(record.get(0));
                    Classroom classroom = classroomMap.computeIfAbsent(classroomId, key-> {
                        Classroom myClassroom = new Classroom(classroomId);
                        grade.getClassrooms().add(myClassroom);
                        return myClassroom;
                    });
                    classroom.setName( getRecordValue( record, hdr, CSV_FIELDS.classroom_name) );

                    //////////////////////////////////
                    // create student
                    //////////////////////////////////
                    Integer studentId = getRecordValueAsInteger( record, hdr, CSV_FIELDS.student_id);
                    Student student = new Student(studentId);
                    student.setFirstName( getRecordValue( record, hdr, CSV_FIELDS.student_first_name));
                    student.setLastName( getRecordValue( record, hdr, CSV_FIELDS.student_last_name) );
                    classroom.getStudents().add(student);

                    //////////////////////////////////
                    // create teacher1
                    //////////////////////////////////
                    Integer teacher1Id = getRecordValueAsInteger( record, hdr, CSV_FIELDS.teacher_1_id);
                    Teacher teacher1 = new Teacher(teacher1Id);
                    teacher1.setFirstName( getRecordValue( record, hdr, CSV_FIELDS.teacher_1_first_name));
                    teacher1.setLastName( getRecordValue(record, hdr, CSV_FIELDS.teacher_1_last_name ));
                    classroom.getTeachers().add(teacher1);

                    //////////////////////////////////
                    // create teacher2
                    //////////////////////////////////
                    Integer teacher2Id = getRecordValueAsInteger( record, hdr,CSV_FIELDS.teacher_2_id);
                    if ( teacher2Id != null ){
                        Teacher teacher2 = new Teacher(teacher2Id);
                        teacher2.setFirstName( getRecordValue(record, hdr, CSV_FIELDS.teacher_2_first_name ));
                        teacher2.setLastName( getRecordValue( record, hdr, CSV_FIELDS.teacher_2_last_name ) );
                        classroom.getTeachers().add(teacher2);
                    }
                }
                return doc;
            }
            catch ( Exception ex ){
                throw new ParseException(ex);
            }
        }
    }

    /**
     * Implementation of XML Formatter
     */
    static class XMLSchoolDocumentFormatterImpl implements SchoolDocumentFormatter {

        public void format(OutputStream os, School school){
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance( School.class );
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, XML_PRETTY_PRINT);
                jaxbMarshaller.marshal( school, os );
            }
            catch ( Exception ex ){
                throw new FormatterException(ex);
            }
        }
    }

    /**
     * Implementation of CSV Formatter
     */
    static class CSVSchoolDocumentFormatterImpl implements SchoolDocumentFormatter {

        void formatHeader( Writer wr )
                throws IOException
        {
            String sf = String.format(
                    "%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
                    CSV_CLASSROOM_ID,
                    CSV_FIELDS.classroom_name,
                    CSV_FIELDS.teacher_1_id,
                    CSV_FIELDS.teacher_1_last_name,
                    CSV_FIELDS.teacher_1_first_name,
                    CSV_FIELDS.teacher_2_id,
                    CSV_FIELDS.teacher_2_last_name,
                    CSV_FIELDS.teacher_2_first_name,
                    CSV_FIELDS.student_id,
                    CSV_FIELDS.student_last_name,
                    CSV_FIELDS.student_first_name,
                    CSV_FIELDS.student_grade
            );
            wr.write(sf);
        }

        void format(Writer wr, Grade grade, Classroom classroom, Teacher teacher1, Teacher teacher2, Student student)
                throws IOException
        {
            String sf = String.format(
                    "%d, %s, %s, %s, %s, %s, %s, %s, %d, %s, %s, %d\n",
                    classroom.getId(),
                    classroom.getName(),
                    teacher1 == null ? "" : teacher1.getId(),
                    teacher1 == null ? "" : teacher1.getLastName(),
                    teacher1 == null ? "" : teacher1.getFirstName(),
                    teacher2 == null ? "" : teacher2.getId(),
                    teacher2 == null ? "" : teacher2.getLastName(),
                    teacher2 == null ? "" : teacher2.getFirstName(),
                    student.getId(),
                    student.getLastName(),
                    student.getFirstName(),
                    grade.getId()
            );
            wr.write(sf);
        }

        public void format(OutputStream os, School school){
            try {
                Writer wr = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                formatHeader(wr);
                for ( Grade grade: school.getGrades() ){
                    for ( Classroom classroom: grade.getClassrooms() ){
                        Stream<Teacher> teachers = classroom.getTeachers().stream();
                        Teacher teacher1 = teachers.findFirst().orElse(null );
                        Teacher teacher2 = teachers.skip(1).findFirst().orElse(null );
                        for ( Student student : classroom.getStudents() ){
                            format(wr, grade, classroom, teacher1, teacher2, student );
                        }
                    }
                }
                wr.flush();
            }
            catch ( Exception ex ){
                throw new FormatterException(ex);
            }
        }
    }

    static class SchoolDocument {
        private FileType fileType;
        private School school;
        public FileType getFileType(){
            return fileType;
        }
        public void setFileType(FileType fileType){
            this.fileType = fileType;
        }
        public School getSchool(){
            return school;
        }
        public void setSchool(School school){
            this.school=school;
        }
    }

    static class SchoolDocumentBuilderImpl implements SchoolDocumentBuilder {
        FileType fileTypeDetect( BufferedReader rdr ){
            try {
                rdr.mark(DETECT_LINE_LEN);
                String hdr = rdr.readLine();
                for ( FileType fileType: FileType.values() ){
                    if ( hdr.startsWith( FILE_TYPE_DETECT_MAP.get(fileType) ) ) {
                        return fileType;
                    }
                }
                return FileType.UNKNOWN;
            }
            catch ( Exception ex ){
                throw new DocumentException(ex);
            }
            finally {
                try { rdr.reset(); } catch (Exception ignore){}
            }
        }

        SchoolDocumentParser getDocumentParser( FileType fileType ){
            try {
                return FILE_TYPE_PARSER_MAP.get(fileType).getDeclaredConstructor().newInstance();
            }
            catch ( Exception ex ){
                throw new DocumentException(ex);
            }
        }

        public SchoolDocument parse( InputStream is ){
            BufferedReader rdr = new BufferedReader(new InputStreamReader(is));
            FileType fileType = fileTypeDetect(rdr);
            return getDocumentParser( fileType ).parse(rdr);
        }
    }

    interface SchoolDocumentBuilder {
        SchoolDocument parse( InputStream ios );
    }

    /**
     *  SchoolDocumentBuilderFactory
     *  This factory class will create a SchoolDocumentBuilder
     *
     *   Usage:
     *   SchoolDocumentBuilderFactory dbFactory = SchoolDocumentBuilderFactory.getInstance();
     *   SchoolDocumentBuilder documentBuilder = dbFactory.newInstance();
     *   SchoolDocument schoolDocument = documentBuilder.parse(System.in);
     */
    static class SchoolDocumentBuilderFactory {
        private static SchoolDocumentBuilderFactory instance;
        private SchoolDocumentBuilderFactory(){}
        public static SchoolDocumentBuilderFactory getInstance(){
            //DCL
            if ( instance == null ){
                synchronized( SchoolDocumentBuilderFactory.class ){
                    if ( instance == null ){
                        instance = new SchoolDocumentBuilderFactory();
                    }
                }
            }
            return instance;
        }
        public SchoolDocumentBuilder newInstance(){
            return new SchoolDocumentBuilderImpl();
        }
    }

    /**
     *  SchoolDocumentFormatterFactory
     *  This class will create a document formatter based on the FileType
     *
     *   Usage:
     *   SchoolDocumentFormatter formatter = SchoolDocumentFormatterFactory.getInstance().getFormatter(FileType.CSV);
     *   formatter.format(System.out, school);
     */
    static class SchoolDocumentFormatterFactory {
        private static SchoolDocumentFormatterFactory instance;
        private SchoolDocumentFormatterFactory(){}

        public static SchoolDocumentFormatterFactory getInstance(){
            //DCL
            if ( instance == null ){
                synchronized( SchoolDocumentFormatterFactory.class ){
                    if ( instance == null ){
                        instance = new SchoolDocumentFormatterFactory();
                    }
                }
            }
            return instance;
        }

        public SchoolDocumentFormatter getFormatter( FileType fileType ){
            try {
                return FILE_TYPE_FORMATTER_MAP.get(fileType).getDeclaredConstructor().newInstance();
            }
            catch ( Exception ex ){
                throw new FormatterException(ex);
            }
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        /* The instructions say files but this states stdin/stdout */
        InputStream is = System.in;
        OutputStream os = System.out;
        SchoolDocumentBuilderFactory dbFactory = SchoolDocumentBuilderFactory.getInstance();
        SchoolDocumentBuilder documentBuilder = dbFactory.newInstance();
        SchoolDocument schoolDocument = documentBuilder.parse(is);
        SchoolDocumentFormatterFactory formatterFactory = SchoolDocumentFormatterFactory.getInstance();
        if ( schoolDocument == null ){
            System.err.println("Invalid School Document(null)");
        }
        else if ( schoolDocument.getFileType() == FileType.XML ){
            formatterFactory.getFormatter(FileType.CSV).format( os, schoolDocument.getSchool());
        }
        else if ( schoolDocument.getFileType() == FileType.CSV ){
            formatterFactory.getFormatter(FileType.XML).format( os, schoolDocument.getSchool());
        }
        else {
            System.err.println("Invalid Document File Type: " + schoolDocument.getFileType());
        }
    }
}