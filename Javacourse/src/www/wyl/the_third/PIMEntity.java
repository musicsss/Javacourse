/**
 *
 *author：wangyulin
 *16130120138
 *mail:1009948721@qq.com
 */
package www.wyl.the_third;


public abstract class PIMEntity {
    String Priority;

    PIMEntity(){
        Priority = "normal";
    }

    // priority can enstablished via this constructor
    //
    PIMEntity(String priority) {
        Priority =  priority;
    }

    // accessor method for getting the priority string
    public String getPriority() {
        return Priority;
    }
    //method that changes the prioority string
    public void setPriority(String p){
        Priority = p;
    }

    //Each PIMEntity needs to be able to set all state information
    //(Fields) from a single text string.
    abstract public void fromString(String s);

    //This is actually alreadyt defined by the super class
    //Object,but redefined here as abstract to make sure
    //that derived classes actually implement it
    abstract public String toString();
    }
