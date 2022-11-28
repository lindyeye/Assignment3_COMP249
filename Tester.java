public class Tester {
    public static void main(String[] args) {
        Article article1 = new Article("J. Park and J. N. James and Q. Li and Y. Xu and W. Huang", "IEEE Transactions on Vehicular Technology", "Optimal DASH-Multicasting over LTE",
        "2018", "PP", "99", "15-27",
        "Forward error correction;Long Term Evolution;Maintenance engineering;Multicast communication;Resource management;Static VAr compensators;Streaming media;DASH;LTE;convex optimization;eMBMS;multicasting",
        "10.1109/TVT.2018.2789899", "0018-9545", "January", 1);

        Article article2 = new Article("J. Park", "IEEE Transactions on Vehicular Technology", "Optimal DASH-Multicasting over LTE",
        "2018", "PP", "99", "15-27",
        "Forward error correction;Long Term Evolution;Maintenance engineering;Multicast communication;Resource management;Static VAr compensators;Streaming media;DASH;LTE;convex optimization;eMBMS;multicasting",
        "10.1109/TVT.2018.2789899", "0018-9545", "January", 1);
        
        System.out.println();
        System.out.println(article1.toIEEE());
        System.out.println();
        System.out.println(article1.toACM());
        System.out.println();
        System.out.println(article2.toNJ());
        System.out.println();
    }
}
