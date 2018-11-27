/**
 *
 *author：wangyulin
 *16130120138
 *mail:1009948721@qq.com
 */
package www.wyl.the_third;


import java.util.Calendar;
import java.util.Date;
import www.wyl.the_second.Cal;

class PIMTodo extends PIMEntity implements dateShare {
  //构造函数
   // private String priority;
    private String date;
    private String notes;

    public PIMTodo() {
        this.date = null;
        this.notes = null;
        this.Priority = null;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public  boolean checkDate(String dates){
        String[] noteDateString=ServiceTool.split_(dates,"/");
        if(Integer.parseInt(noteDateString[2])<1970)
            return false;
        if(Integer.parseInt(noteDateString[0])>12||Integer.parseInt(noteDateString[0])<1)
            return false;
        try {
            if(Integer.parseInt(noteDateString[1])<1||Integer.parseInt(noteDateString[1])> Cal.returnDateOfMonth(noteDateString[0],noteDateString[1]))
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public int[] getNowTime(){
        return null;
    };
    //tostring
    public String toString(){

        return  "TODO"+" "+this.Priority+" "+this.date+" "+this.notes;
    }

    public void fromString(String s){
        try{
            String[] returnContent = s.split(";");
            this.date = returnContent[0];
            this.notes = returnContent[1];
            this.Priority = returnContent[2];
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("请按照正确格式输入！");

        }
    }
}

class PIMNote extends PIMEntity{
     //private String  priority;//优先级
     private String  content; //内容

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString(){

         return "NOTE"+" "+this.getPriority()+" "+this.content;
     }

     public void fromString(String s){
         try{
             String[] returnContent = s.split(";");
             this.content = returnContent[1];
             this.content = returnContent[0];
         }catch (Exception e){
             e.printStackTrace();
             System.out.println("请按照正确格式输入！");

         }

     }
}

class PIMAppointment  extends PIMEntity implements dateShare{
     private String date;//日期
     private String description;//描述

    public String getDate() {
        return date;
    }
    public  boolean checkDate(String date){
        String[] dateString=ServiceTool.split_(date,"/");
        if(Integer.parseInt(dateString[2])<1970)
            return false;
        if(Integer.parseInt(dateString[0])>12||Integer.parseInt(dateString[0])<1)
            return false;
        try {
            if(Integer.parseInt(dateString[1])<1||Integer.parseInt(dateString[1])> Cal.returnDateOfMonth(dateString[0],dateString[1]))
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){

        return " APPOINTMENT"+" "+this.Priority+" "+this.date+" "+this.description;
    }

    public void fromString(String s){
        try{
            String[] returnContent = s.split(";");
            this.date = returnContent[0];
            this.description = returnContent[1];
            this.Priority = returnContent[2];
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("请按照正确格式输入！");

        }
    }
    public int[] getNowTime(){
        Calendar c= Calendar.getInstance();
        int[] now={c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE)};
        return now;
    }

    public String dateToString(int[] time){
        String str ;
        str=time[0]+"-"+time[1]+"-"+time[2];
        return str;
    }
}

class PIMContact extends PIMEntity{
    private String firstName;//名字
    private String lastName;//姓氏
    private String emailAddress;//电子邮件

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String toString(){

        return "CONTACT"+" "+this.Priority+" "+this.firstName+" "+this.lastName+" "+this.emailAddress;
    }

    public void fromString(String s){
        try{
            String[] returnContent = s.split(";");
            this.firstName = returnContent[0];
            this.lastName = returnContent[1];
            this.emailAddress = returnContent[2];
            this.Priority = returnContent[3];
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("请按照正确格式输入！");

        }
    }
}
