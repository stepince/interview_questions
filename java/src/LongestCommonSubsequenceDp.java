/*

https://leetcode.com/problems/longest-common-subsequence/

LCS for input Sequences "ABCDGH" and "AEDFHR" is "ADH" of length 3.
LCS for input Sequences "AGGTAB" and "GXTXAYB" is "GTAB" of length 4


Note : find1 runs much faster

*/

public class LongestCommonSubsequenceDp {

    private static int find1(char[] chars1, int idx1, char[] chars2, int idx2, Integer[][] mem ){
        if ( idx1 == chars1.length || idx2 == chars2.length ) return 0;
        if ( mem[idx1][idx2] != null ) return mem[idx1][idx2];
        int max =  ( chars1[idx1] == chars2[idx2] )
                ? ( 1 + find1(chars1,idx1+1, chars2,idx2+1, mem ) )
                :  Math.max(find1(chars1,idx1+1, chars2,idx2, mem ), find1(chars1,idx1, chars2,idx2+1, mem ) );
        return mem[idx1][idx2] = max;
    }

    public static int find1(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        return find1(chars1, 0, chars2, 0, new Integer[chars1.length][chars2.length]);
    }

    // note: avoid this type of pattern, this is ok if you can reduce the size of mem
    // see LongestIncreasingSubsequence for an example
    private static int find2(char[] chars1, int idx1, char[] chars2, int idx2, Integer[][] mem ){
        if ( idx1 >= chars1.length  || idx2 >= chars2.length ) return 0;
        if ( mem[idx1][idx2] != null ) return  mem[idx1][idx2];
        int max = 0;
        for ( int i = idx1; i < chars1.length; ++i ){
            for ( int j = idx2; j < chars2.length; ++j ){
                if ( chars1[i] == chars2[j] ) {
                    max = Math.max(max, 1 + find2( chars1, i+1, chars2, j+1, mem ));
                }
            }
        }
        return mem[idx1][idx2] = max;
    }

    public static int find2(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        return find2( chars1, 0, chars2, 0,new Integer[chars1.length][chars2.length]);
    }

    public static void main(String[] args) {
//        String str1 = args[0];
//        String str2 = args[1];
//        String str1= "ABCDGH";
//        String str2= "AEDFHR";

        String str1 = "fcvafurqjylclorwfoladwfqzkbebslwnmpmlkbezkxoncvwhstwzwpqxqtyxozkpgtgtsjobujezgrkvevklmludgtyrmjaxyputqbyxqvupojutsjwlwluzsbmvyxifqtglwvcnkfsfglwjwrmtyxmdgjifyjwrsnenuvsdedsbqdovwzsdghclcdexmtsbexwrszihcpibwpidixmpmxshwzmjgtadmtkxqfkrsdqjcrmxkbkfoncrcvoxuvcdytajgfwrcxivixanuzerebuzklyhezevonqdsrkzetsrgfgxibqpmfuxcrinetyzkvudghgrytsvwzkjulmhanankxqfihenuhmfsfkfepibkjmzybmlkzozmluvybyzsleludsxkpinizoraxonmhwtkfkhudizepyzijafqlepcbihofepmjqtgrsxorunshgpazovuhktatmlcfklafivivefyfubunszyvarcrkpsnglkduzaxqrerkvcnmrurkhkpargvcxefovwtapedaluhclmzynebczodwropwdenqxmrutuhehadyfspcpuxyzodifqdqzgbwhodcjonypyjwbwxepcpujerkrelunstebopkncdazexsbezmhynizsvarafwfmnclerafejgnizcbsrcvcnwrolofyzulcxaxqjqzunedidulspslebifinqrchyvapkzmzwbwjgbyrqhqpolwjijmzyduzerqnadapudmrazmzadstozytonuzarizszubkzkhenaxivytmjqjgvgzwpgxefatetoncjgjsdilmvgtgpgbibexwnexstipkjylalqnupexytkradwxmlmhsnmzuxcdkfkxyfgrmfqtajatgjctenqhkvyrgvapctqtyrufcdobibizihuhsrsterozotytubefutaxcjarknynetipehoduxyjstufwvkvwvwnuletybmrczgtmxctuny";
        String str2 = "nohgdazargvalupetizezqpklktojqtqdivcpsfgjopaxwbkvujilqbclehulatshehmjqhyfkpcfwxovajkvankjkvevgdovazmbgtqfwvejczsnmbchkdibstklkxarwjqbqxwvixavkhylqvghqpifijohudenozotejoxavkfkzcdqnoxydynavwdylwhatslyrwlejwdwrmpevmtwpahatwlaxmjmdgrebmfyngdcbmbgjcvqpcbadujkxaxujudmbejcrevuvcdobolcbstifedcvmngnqhudixgzktcdqngxmruhcxqxypwhahobudelivgvynefkjqdyvalmvudcdivmhghqrelurodwdsvuzmjixgdexonwjczghalsjopixsrwjixuzmjgxydqnipelgrivkzkxgjchibgnqbknstspujwdydszohqjsfuzstyjgnwhsrebmlwzkzijgnmnczmrehspihspyfedabotwvwxwpspypctizyhcxypqzctwlspszonsrmnyvmhsvqtkbyhmhwjmvazaviruzqxmbczaxmtqjexmdudypovkjklynktahupanujylylgrajozobsbwpwtohkfsxeverqxylwdwtojoxydepybavwhgdehafurqtcxqhuhkdwxkdojipolctcvcrsvczcxedglgrejerqdgrsvsxgjodajatsnixutihwpivihadqdotsvyrkxehodybapwlsjexixgponcxifijchejoxgxebmbclczqvkfuzgxsbshqvgfcraxytaxeviryhexmvqjybizivyjanwxmpojgxgbyhcruvqpafwjslkbohqlknkdqjixsfsdurgbsvclmrcrcnulinqvcdqhcvwdaxgvafwravunurqvizqtozuxinytafopmhchmxsxgfanetmdcjalmrolejidylkjktunqhkxchyjmpkvsfgnybsjedmzkrkhwryzan";
        long t = System.currentTimeMillis();
        System.out.println( find1(str1, str2));
        System.out.println("time 1 = " + ( System.currentTimeMillis() - t ) );

        t = System.currentTimeMillis();
        System.out.println( find2(str1, str2));
        System.out.println("time 2 = " + ( System.currentTimeMillis() - t ) );
    }
}