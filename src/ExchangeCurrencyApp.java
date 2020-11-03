public class ExchangeCurrencyApp {

    public static void main(String[] args) {

       ExchangeAPI ex = new ExchangeAPI();

       //test connection with THB
        if (ex.getConnection("THB"));
            System.out.println(ex.getResult());



    }


}
