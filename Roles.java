

public class Roles
{
  // arguments are passed using the text field below this editor
  public static void main(String[] args)
  {
        String [] roles= {
            "Городничий","Аммос Федорович",
            "Артемий Филиппович",
            "Лука Лукич"};
         String [] textLines={
            "Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
            "Аммос Федорович: Как ревизор?",
            "Артемий Филиппович: Как ревизор?",
            "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
            "Аммос Федорович: Вот те на!",
            "Артемий Филиппович: Вот не было заботы, так подай!",
            "Лука Лукич: Господи боже! еще и с секретным предписаньем!"};

    StringBuilder res = new StringBuilder("");
    
    for (String role : roles) {
        
        res.append(role + ":\n");
        for (int i = 0; i < textLines.length; i++) {
            if ( textLines[i].matches("^"+role+":.*") ){
                res.append(String.valueOf(i+1) + ")" + textLines[i].substring(role.length()+1));
            }
        }
        res.append("\n");
    }
        System.out.println( res.toString());
    
  }
}