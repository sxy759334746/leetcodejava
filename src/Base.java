public class Base
{
    private String baseName = "base";
    public Base(){
        System.out.println(this.baseName);
        this.callName();
    }
 
    public void callName(){
        System.out.println(baseName);
    }
 
    static class Sub extends Base{
        private String baseName = "sub";
        public void callName(){
            System.out.println (this.baseName) ;
        }
    }
    public static void main(String[] args){
        Base b = new Sub();
    }
}