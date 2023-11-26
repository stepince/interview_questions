import java.util.*;
/*
  union-find
  space: O( N )
  merge: O( a(N))
  search: O( a(N) )
 */
public class MergeAccounts {

    static final class Account {
        String name;
        String email;
        Set<String> emails = new HashSet<>();
        Account parent;
        Account(String name, String email){
            this.name = name;
            this.parent = this;
            this.email = email;
        }
    }

    final Account findParent( Account acct  ){
        while ( acct.parent != acct  ){
            acct = acct.parent;
        }
        return acct;
    }

    void collectAll( Map<String, Account> acctMap, Account acct, List<String> visited ){
        visited.add(acct.email);
        for ( String email: acct.emails ){
            // no need to check for cycle
            collectAll(acctMap,acctMap.get(email),visited);
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Account> acctMap = new HashMap<>();
        for ( List<String> acctInfo : accounts ){
            final String name = acctInfo.get(0);
            final String acctEmail = acctInfo.get(1);
            Account acct = acctMap.get(acctEmail);
            Account parentAcct = acct == null ? acctMap.computeIfAbsent(acctEmail, (key)-> new Account(name,acctEmail) ) : findParent(acct);
            for ( int i = 2; i < acctInfo.size();++i ){
                String email = acctInfo.get(i);
                acct = acctMap.get(email);
                if ( acct == null ){
                    parentAcct.emails.add(email);
                    acctMap.computeIfAbsent( email, (key)->new Account(name,email)).parent = parentAcct;
                    continue;
                }
                Account oldParent = findParent(acct);
                if ( oldParent != parentAcct ){
                    oldParent.parent = parentAcct;
                    parentAcct.emails.add(oldParent.email);
                }
            }
        }

        List<List<String>> newList = new ArrayList<>();
        for( Account acct: acctMap.values() ){
            if ( acct.parent == acct ){
                List<String> visited = new ArrayList<>();
                collectAll( acctMap, acct, visited );
                Collections.sort(visited);
                visited.add(0,acct.name);
                newList.add( visited );
            }
        }
        return newList;
    }

    public List<List<String>> accountsMerge(String[][] accounts) {
        List<List<String>> accountsList = new ArrayList<>();
        for( String[] account: accounts){
            accountsList.add( Arrays.asList(account) );
        }
        return accountsMerge(accountsList) ;
    }

    public static void main(String[] args){
        String[][] accounts = {
                {"David","David0@m.co","David4@m.co","David3@m.co"},
                {"David","David5@m.co","David5@m.co","David0@m.co"},
                {"David","David1@m.co","David4@m.co","David0@m.co"},
                {"David","David0@m.co","David1@m.co","David3@m.co"},
                {"David","David4@m.co","David1@m.co","David3@m.co"}};
        MergeAccounts solution = new MergeAccounts();
        List<List<String>> ans = solution.accountsMerge(accounts);
        for ( List<String> l : ans ){
            System.out.println(l);
        }
    }
}
