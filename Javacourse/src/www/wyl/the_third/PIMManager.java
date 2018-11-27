/**
 *
 *author：wangyulin
 *16130120138
 *mail:1009948721@qq.com
 */
package www.wyl.the_third;

import java.io.*;
import java.util.Date;

public class PIMManager {
    private final static String PATH="C:\\Users\\10099\\IdeaProjects\\Javacourse\\src\\www\\wyl\\the_third\\theThirdTestDoc.txt";
    public PIMManager(){
        for(int i=0;;i++){

            Service service = new Service(PATH);
            for(int j =0;;j++){
                Print.initPrint();
                service.init();
            }

        }
    }


    public static void main(String[] args) {

        PIMManager tes = new PIMManager();
       // ServiceTool.writeFile(PATH,"Hello world!");
    }

}

//输入选项
class Input{
    private String inputValue =null;

    public static int InputCheck(String input){
        if(input.equals("List")){
            return 1;
        }
        if(input.equals("Create")){
            return 2;
        }
        if(input.equals("Save")){
            return 3;
        }
        if(input.equals("Load")){
            return 4;
        }
        if(input.equals("Quit")){
            return 5;
        }else
            return 0;
    }
    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }
}


class Print{
    public static int initPrint(){
        try{
            System.out.println("");
            System.out.println("Welcome to PIM");
            System.out.println("---Enter a command (supported commads are List Create Save Load Quit");
        }catch (Exception e){
            return 1;
        }
        return 0;
    }
    //无用
    public static int listPrint(int item){
       try{
           System.out.printf("There are(is) %d item(s)",item);
       }catch (Exception e){
           return 1;
       }
       return 0;
    }
}
class Service{
    private String path ;
    private int status=0 ;//状态码
    private int tmp=0;//零时变量
    private String[] item = new String[100];//存储数组
    public Service(String path){
        for(int i=0;i<this.item.length;i++){
            item[i]="";
        }
        this.path = path;
    }

    public void init(){
        String command = ServiceTool.input();
        int status = Input.InputCheck(command);
        switch (status){
            case 0:
                System.out.println("There is a error in the command you inputed !");break;
            case 1:
                showList();break;
            case 2:
                item[tmp]=createOperating();
                tmp+=1;
                break;
            case 3:
                saveItem();break;
            case 4:
                loadItem();
                break;
            case 5:
                System.out.println("Bye!");
                System.exit(0);break;
        }


    }
    //Save操作
    public void saveItem(){
        if(this.item[0].equals("")){
            System.out.println("Nothing can be save!!");
            return;
        }
        String[] str=new String[ServiceTool.returnStringArrayNotNull_Length(this.item)];
        for(int i=0;i<ServiceTool.returnStringArrayNotNull_Length(this.item);i++)
        {
            str[i]=ServiceTool.assembly(this.item[i]);
        }
        for(int i=0;i<str.length;i++){
            ServiceTool.writeFile(path,str[i]);
        }
    }
    //Load操作
    public void loadItem(){
        ServiceTool.loadPrint(ServiceTool.fileRead(path));
    }
    //Create操作
    public String createOperating(){
        for(int i = 0;;i++){
            System.out.println("Enter an item type ( todo,note,contact or appointment )");
            final String OP = ServiceTool.input();
            if(OP.equals("todo")) {
                PIMTodo todo = new PIMTodo();
                System.out.println("Enter date for todo item:");
                String date;
                for(int j=0;;j++)
                {
                    date=ServiceTool.input();
                   if(todo.checkDate(date))
                       break;
                   System.out.println("There is something wrong in the date you inputed!Try again!");

                }
                todo.setDate(date);
                System.out.println("Enter todo text:");
                todo.setNotes(ServiceTool.input());
                System.out.println("Enter todo priority");
                todo.Priority=ServiceTool.input();
                return todo.toString();

            }
            if(OP.equals("note")){
                PIMNote note = new PIMNote();
                System.out.println("Enter the content of the note:");
                note.setContent(ServiceTool.input());
                System.out.println("Enter note priority:");
                note.Priority=ServiceTool.input();
                return note.toString();

            }
            if(OP.equals("contact")){
                PIMContact contact = new PIMContact();
                System.out.println("Enter the firstName of the contact");
                contact.setFirstName(ServiceTool.input());
                System.out.println("Enter the lastName of the contact");
                contact.setLastName(ServiceTool.input());
                System.out.println("Enter the emailAddress of the contact");
                contact.setEmailAddress(ServiceTool.input());
                System.out.println("Enter contact priority");
                contact.Priority=ServiceTool.input();
                return contact.toString();
            }
            if(OP.equals("appointment")){
                PIMAppointment appointment = new PIMAppointment();
                System.out.println("Enter the date of the appointment:");
                String date;
                for(int j=0;;j++){
                    date=ServiceTool.input();
                    if(appointment.checkDate(date))
                        break;
                    System.out.println("There is something wrong in the date you inputed!");
                }
                appointment.setDate(date);
                System.out.println("Enter the description of the appointment:");
                appointment.setDescription(ServiceTool.input());
                System.out.println("Enter appointment priority:");
                appointment.Priority=ServiceTool.input();
                return appointment.toString();
            }else{
                System.out.println("we find you inputed an Error operating");
                System.out.println("Please try again !");//or input exit to exit this step of operating
            }
        }

    }
    //List操作
    public void showList(){
      //  System.out.println(item[0]+"打印此句");
       int itemNum=ServiceTool.returnStringArrayNotNull_Length(this.item);
       System.out.println("There are "+itemNum+" item(s)");
       for(int i=0;i<itemNum;i++){
           if(this.item[i].equals(""))
           {
               System.out.println("null");
               break;
           }
           System.out.println(this.item[i]);
       }

    }

}

class ServiceTool{
    public static void printString(String[] str){
        for(int i=0;i<str.length;i++){
            if(str[i].equals(null))
            {
                System.out.println("null");
                break;

            }
            if(str[i].equals("TODO")||str[i].equals("NOTE")||str[i].equals("CONTACT")||str[i].equals("APPOINTMENT")){
                System.out.println(" ");
            }

            System.out.print(str[i]);
            System.out.print(" ");
        }
    }
    //切割字符串
    public static String[] split_(String str,String cha){
        String[] returnSplit;
        returnSplit=str.split(cha);
        return returnSplit;
    }
    //获取输入内容
    public static String input(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        }catch (Exception e){
            e.printStackTrace();
            return "Error!";
        }
    }
    public  static int returnStringArrayNotNull_Length(String [] item){
        int length ;
        for(int i= 0;i<item.length;i++){
            if(item[i].equals("")) {
                length = i ;
                return length;
            }
        }
        return length=item.length;
    }
    //获取当前执行操作
    //public static void getOperating

    public static String[] fileRead(String path) {
        try{
            File file = new File(path);
            FileReader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            String s = "";
            String[] str = new String[100];
//            for(int i=0;;i++){
//                if(bReader.readLine()==null)
//                    break;
//                str[i]=bReader.readLine();
//                 sb.append(str[i]+"\n");
//                System.out.println(str[i]);
//            }
            int i =0;
            while ((str[i] =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(str[i] + "\n");//将读取的字符串添加换行符后累加存放在缓存中
//                System.out.println(str[i]);
                i++;
            }
            bReader.close();

            return str;
        }catch (Exception e){

            e.printStackTrace();
            return null;
        }
    }
    //格式打印读取到的结果
    public static void loadPrint(String[] str){
        for(int i=0;;i++){
            if(str[i]==null)
                break;
            String[] str_=ServiceTool.split_(str[i],"&");
            System.out.println("there are "+str_.length+" items");
            for(int j =0;j<str_.length;j++)
            {
                String[] itemStr=ServiceTool.split_(str_[j],";");
                System.out.print("ITEM "+(j+1)+" :");
                ServiceTool.printString(itemStr);
                System.out.println("");
            }

        }
    }
//文件写入操作。
    public static void writeFile(String filePath,String content){
        BufferedWriter out = null;
        try{
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,true)));
            out.write(content);
        }catch (Exception  e){
            e.printStackTrace();
        }finally {
            try{
                if(out!=null){
                    out.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    //组装存储
    public static String assembly(String str){
        String[] split;
        String str1="";
        split=str.split(" ");
        for(int i= 0;i<split.length;i++){
            str1=str1+split[i]+";";
        }
        str1+="&";
        return str1;
    }

//    public static void itemSplit(){
//
//    }
}