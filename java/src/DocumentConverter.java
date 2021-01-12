import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
/*
     Interview question to convert a file from one format to another
     xml -> csv
     csv -> xml

add jaxb-api lib
FIXME: should add mvn pom.xml
 */
public class DocumentConverter {

    final static boolean XML_PRETTY_PRINT = true;
    final static String XML_HDR = "<?xml";
    final static String CSV_CLASSROOM_ID = "classroom id";
    final static String CSV_SPLIT_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    final static int DETECT_LEN;
    static {
        DETECT_LEN = Math.max(XML_HDR.length(), CSV_CLASSROOM_ID.length() );
    }

    enum FileType {
        XML, CSV
    }

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

    static class ParseExeption extends RuntimeException {
        private static final long serialVersionUID = 1;
        private Exception cause;
        public ParseExeption(Exception ex ){
            this.cause = ex;
        }
    }

    static class FormatterException extends RuntimeException {
        private static final long serialVersionUID = 1;
        private Exception cause;
        public FormatterException(Exception ex ){
            this.cause = ex;
        }
    }

    static class DocumentException extends RuntimeException {
        private static final long serialVersionUID = 1;
        private Exception cause;

        public DocumentException(Exception ex ){
            this.cause = ex;
        }
        public DocumentException( String mesg ){
            super(mesg);
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

        public Grade(){
        }
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
        public Classroom(){
        }
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
        public Student(){
        }
        public Student(Integer id){
            this.id=id;
        }
    }

    @XmlRootElement(name = "teacher")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Teacher extends Person implements Serializable {
        private static final long serialVersionUID = 1L;
        public Teacher(){
        }
        public Teacher(Integer id){
            this.id=id;
        }
    }

    /*
     *   New File types will have to implement this interface
     *
     */

    interface SchoolDocumentParser {
        SchoolDocument parse( Reader rdr );
    }

    /*
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
                throw new ParseExeption(ex);
            }
        }
    }

    /*
       Implementation of CSV Document Parser
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
            for ( String val: line.split(",") ){
                record.add(val.trim());
            }
            return record;
        }

        public SchoolDocument parse( Reader rdr ){
            try {
                final Map<Integer,Classroom> classroomMap = new HashMap<>();
                final Map<Integer,Grade> gradeMap = new HashMap<>();

                SchoolDocument doc =  new SchoolDocument();
                doc.setFileType(FileType.CSV);
                School school = new School();

                doc.setSchool(school);
                Scanner scanner = new Scanner(rdr);
                if ( !scanner.hasNextLine() ) throw new Exception("Invalid CSV");

                Map<String,Integer> hdr = getHeader(scanner.nextLine());
                while (scanner.hasNextLine()) {
                    List<String> values = getRecordFromLine(scanner.nextLine());

                    //////////////////////////////////
                    // create grade
                    //////////////////////////////////

                    Integer gradeId = Integer.valueOf(values.get( hdr.get( CSV_FIELDS.student_grade.name()) ));
                    final Grade grade = gradeMap.computeIfAbsent(gradeId,
                            key-> {
                                    Grade myGrade  = new Grade(gradeId);
                                    school.getGrades().add(myGrade);
                                    return myGrade;
                            });

                    //////////////////////////////////
                    // create classroom
                    //////////////////////////////////

                    // classroomId is always at index 0
                    Integer classroomId = Integer.valueOf(values.get(0));
                    Classroom classroom = classroomMap.computeIfAbsent(classroomId,
                            key-> {
                                Classroom myClassroom  = new Classroom(classroomId);
                                grade.getClassrooms().add(myClassroom);
                                return myClassroom;
                            });
                    classroom.setName( values.get(hdr.get( CSV_FIELDS.classroom_name.name())  ));

                    //////////////////////////////////
                    // create student
                    //////////////////////////////////
                    Integer studentId = Integer.valueOf(values.get( hdr.get( CSV_FIELDS.student_id.name()) ));
                    Student student = new Student(studentId);
                    student.setFirstName( values.get( hdr.get( CSV_FIELDS.student_first_name.name()) ));
                    student.setLastName( values.get( hdr.get( CSV_FIELDS.student_last_name.name()) ));
                    classroom.getStudents().add(student);

                    //////////////////////////////////
                    // create teacher1
                    //////////////////////////////////
                    Integer teacher1Id = Integer.valueOf(values.get( hdr.get( CSV_FIELDS.teacher_1_id.name()) ));
                    Teacher teacher1 = new Teacher(teacher1Id);
                    teacher1.setFirstName( values.get( hdr.get( CSV_FIELDS.teacher_1_first_name.name()) ));
                    teacher1.setLastName( values.get( hdr.get( CSV_FIELDS.teacher_1_last_name.name()) ));
                    classroom.getTeachers().add(teacher1);

                    //////////////////////////////////
                    // create teacher2
                    //////////////////////////////////
                    String teacher2IdStr = values.get(hdr.get( CSV_FIELDS.teacher_2_id.name()));
                    if ( !isBlank(teacher2IdStr) ){
                        Integer teacher2Id = Integer.valueOf( teacher2IdStr );
                        Teacher teacher2 = new Teacher(teacher2Id);
                        teacher2.setFirstName( values.get( hdr.get( CSV_FIELDS.teacher_2_first_name.name()) ));
                        teacher2.setLastName( values.get( hdr.get( CSV_FIELDS.teacher_2_last_name.name()) ));
                        classroom.getTeachers().add(teacher2);
                    }
                }
                return doc;
            }
            catch ( Exception ex ){
                throw new ParseExeption(ex);
            }
        }
    }

    /*
     *  SchoolDocumentFormatter Interface
     *  New file types will have implement this Interface
     *
     */
    interface SchoolDocumentFormatter {
        void format(OutputStream os, School school);
    }

    /*
     * Implmentation of XML Formatter
     *
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

    /*
     * Implmentation of CSV Formatter
     *
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
                        List<Teacher> teachers = classroom.getTeachers();
                        Teacher teacher1 = teachers.size() > 0 ? teachers.get(0) : null;
                        Teacher teacher2 = teachers.size() > 1 ? teachers.get(1) : null;
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
        String readDetect( BufferedReader rdr ){
            try {
                rdr.mark(DETECT_LEN);
                StringBuilder sb = new StringBuilder();
                for ( int i = 0; i < DETECT_LEN; ++i ){
                    sb.append((char)rdr.read());
                }
                rdr.reset();
                return sb.toString().toLowerCase();
            }
            catch ( Exception ex ){
                throw new DocumentException(ex);
            }
        }

        public SchoolDocument parse( InputStream is ){
            BufferedReader rdr = new BufferedReader(new InputStreamReader(is));
            String detectStr = readDetect(rdr);
            if ( detectStr.startsWith(XML_HDR)  ){
                return new XMLSchoolDocumentParserImpl().parse( rdr );
            }
            else if ( detectStr.startsWith(CSV_CLASSROOM_ID) ){
                return new CSVSchoolDocumentParserImpl().parse( rdr );
            }
            else {
                throw new DocumentException("Invalid Document Type");
            }
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
     *  SchoolDocument schoolDocument = documentBuilder.parse(System.in);
     *
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

        public SchoolDocumentFormatter getFormatter( FileType type ){
            if ( type == FileType.CSV ){
                return new CSVSchoolDocumentFormatterImpl();
            }
            else if ( type == FileType.XML  ){
                return new XMLSchoolDocumentFormatterImpl();
            }
            else {
                return null;
            }
        }
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        /* The instructions say files but this states stdin/stdout */

        InputStream is = System.in;
        OutputStream os = System.out;

        SchoolDocumentBuilderFactory dbFactory = SchoolDocumentBuilderFactory.getInstance();
        SchoolDocumentBuilder documentBuilder = dbFactory.newInstance();
        SchoolDocument schoolDocument = documentBuilder.parse(is);
        SchoolDocumentFormatterFactory formatterFactory = SchoolDocumentFormatterFactory.getInstance();
        if ( schoolDocument == null ){
            System.err.println("Invalid School Document(Null)");
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