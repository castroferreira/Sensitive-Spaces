
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;

/**
 *
 * @author almis
 */
public class Test {
    
private static class MyTimeTask extends TimerTask{
    


    public void run()
    {
        Scanner sc = new Scanner(System.in);
        String key;
      key=sc.nextLine();
      int count =0;
        if(key.equals("Comma") || key.equals("Period") || key.length() == 1){
		            key = sc.nextLine();
			    count++;
                            System.out.println(count);
	} else{ 
             key = sc.nextLine(); 
      }
    }
}

public static void main(String[] args) throws ParseException {

    Timer t = new Timer();
t.schedule(new TimerTask() {
    @Override
    public void run() {
       System.out.println("Hello World");
    }
}, 0, 5000);
}
}