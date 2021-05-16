package tr.edu.yildiz.serhadcaliskan;


public class LoggedInUser {

    private static LoggedInUser instance=null;
    private static User currentUser=null;


    public static LoggedInUser getInstance(){

        if(instance==null){

            instance=new LoggedInUser();

        }

        return instance;
    }
    public static User getUser(){

        return currentUser;

    }

    public static void logInUser(User user){

        currentUser=user;
    }



}
