package tt;

public class Test {
    public static void main(String[] args) {
        String s = "https://pss-cep-templates.attprotech.com/Templates/ATT/1.0/campaigns/8/4.0/7-cool-things-android/index.html?campaignId=ac4e52dd-7821-49ce-9f62-b78d086617e6&channelMessageId=004f6ced-713e-4ec8-a572-11376c61ea3a";
        System.out.println(s.substring(s.indexOf("com")+3, s.indexOf("?")));

        ///Templates/ATT/1.0/campaigns/8/4.0/7-cool-things-android/index.html
        ///Templates/ATT/1.0/campaigns/8/4.0/7-cool-things-android/index.html
    }
}
