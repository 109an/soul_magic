package soul_magic.soul_magic.spells;

public class SpellResult {
    public boolean issuccessfull;
    public String message;
    public void Setmessage(String message){
        this.message = message;
    }
    public void fail(){
        this.issuccessfull = false;
    }
    public void success(){
        this.issuccessfull = true;
    }
}
