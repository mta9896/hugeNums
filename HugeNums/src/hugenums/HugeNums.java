/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hugenums;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author MT
 */

public class HugeNums 
{
    private char c1[];
    private char c2[];
    String number1 = "11";
    String number2 = "10";
    private ArrayList<Integer> no1;
    private ArrayList<Integer> no2;
    private ArrayList<Integer> result;
    private int operand;
    private boolean resultIsNegative = false;
    public HugeNums()
    {
        no1 = new ArrayList<Integer>();
        no2 = new ArrayList<Integer>();
        result = new ArrayList<Integer>(); 
        for( int i=0; i<40; i++)
        {
            no1.add(i,0);
            no2.add(i,0);
            result.add(i,0); // fek konam resuit bayad bishtar behesh 0 bedim masalan 80 ta
        }  
    }
    public void chooseOper(int o)
    {
        this.operand = o;
        switch(operand)
        {
            case 1: plusOrSub(); break;
            case 2: plusOrSub(); break;
            case 3: product(no1,no2); break;    
        }
    }
    public boolean isNegative(char c[])
    {
        boolean r;
        if( c[0] == '-')
            r=true;
        else
            r=false;
        return r;
    }
    
    public boolean isZero(ArrayList<Integer> numberArray)
    {
        boolean r = true;
        for(int i=0; i<40; i++ )
        {
            if(numberArray.get(i)!= 0)
                r = false;
        }
        return r;
    }
   
    public boolean isEqual()
    {
        int equalDigits = 0;
        if( (Objects.equals(c1.length, c2.length)))
        {
            for(int i= c1.length -1; i>=0; i--)
            {
                if(Objects.equals(no1.get(i), no2.get(i)))
                    equalDigits++;
            }
        }
        if( equalDigits == c1.length)
            return true;
        else
            return false;
    }
    
    public boolean no1greaterThanNo2() // vaghti 2 adad mosavi ya gharine bashan dochare moshkel mishe inja
    {
        boolean r = true;
        int greaterSize;
        if(no1.size() > no2.size()) // length ha ro size arraylist kardam
            greaterSize = no1.size();
        else
            greaterSize = no2.size();
        int i =1;
        if( no1.get(greaterSize-1) > no2.get(greaterSize-1) )
            r=true;
        if( no1.get(greaterSize-1) < no2.get(greaterSize-1) )
            r = false;
        if(Objects.equals(no1.get(greaterSize-1), no2.get(greaterSize-1))) // inja ro equal kard == bood
        {
            while(Objects.equals(no1.get(greaterSize-i), no2.get(greaterSize-i)))  
            {
                i++;
                if( no1.get(greaterSize-i) > no2.get(greaterSize-i) )
                    r = true;
                if( no1.get(greaterSize-i) < no2.get(greaterSize-i) )
                    r = false;
            }
        }
        return r;
    }
    
    public void plusOrSub()
    {
        if(operand == 1)
        {
            if( (!isNegative(c1)) && (!isNegative(c2)))
                plus(no1,no2);
            if((!isNegative(c1)) && (isNegative(c2)))
            {
                if( no1greaterThanNo2())
                    subtract(no1,no2);
                if(!no1greaterThanNo2())
                {
                    subtract(no2,no1);
                    resultIsNegative = true;
                }
            }
            if((isNegative(c1)) && (!isNegative(c2)))
            {
                if( no1greaterThanNo2())
                    subtract(no2,no1); 
                if(!no1greaterThanNo2())
                {
                    subtract(no2,no1); 
                    resultIsNegative = true;
                }
            }
            if((isNegative(c1)) && (isNegative(c2)))
            {
                plus(no1,no2); 
                resultIsNegative = true;
            }
        }
        if(operand == 2)
        {
            if( (!isNegative(c1)) && (isNegative(c2)))
                plus(no1,no2);
            if( (!isNegative(c1)) && (!isNegative(c2)))
            {
                if( no1greaterThanNo2())
                    subtract(no1,no2);
                if(!no1greaterThanNo2())
                {
                    subtract(no2,no1);
                    resultIsNegative = true;
                }
            }
            if( (isNegative(c1)) && (!isNegative(c2)))
            {
                plus(no1,no2); 
                resultIsNegative = true;
            }
            if( (isNegative(c1)) && (isNegative(c2)))
            {
                 if( no1greaterThanNo2())
                    subtract(no2,no1); 
                if(!no1greaterThanNo2())
                {
                    subtract(no2,no1);
                    resultIsNegative = true;
                }
            }
        }
            
    }
    public void convert()
    {
        c1 = number1.toCharArray();
        for( int i=number1.length()-1; i>0; i--)
        {
            no1.add(number1.length()-1-i,(int) c1[i] - 48 );
        }
        if((int)c1[0] != 45)
            no1.add(number1.length() - 1, (int) c1[0] - 48);
        c2 = number2.toCharArray();
        for( int i=number2.length()-1; i>0; i--)
        {
            no2.add(number2.length()-1-i,(int) c2[i] - 48 );
        }
        if((int)c2[0] != 45)
            no2.add(number2.length() - 1, (int) c2[0] - 48);
        //System.out.println("is Zero "+isZero(no1));
    }
    public void print()
    { 
           System.out.println(no1);
           System.out.println(no2);
           System.out.println("result="+result); 
           if( resultIsNegative == true)
               System.out.print("-");
           for(int i=20; i>=0; i--) // yani beshinam andaze result ro hesab konam???
           {
               System.out.print(result.get(i)); 
           }
           System.out.println("");
    }
    
    public void plus(ArrayList<Integer> first, ArrayList<Integer> second)
    {
        for(int i=0; i<41; i++) // fe'lan ino 40 gozashtam badan 41 mikONAM
        {
            result.add(i, result.get(i)+(first.get(i)+ second.get(i))%10);
            result.add(i+1, ((first.get(i)+ second.get(i))/10));
        }
    }
    public void subtract(ArrayList<Integer> first, ArrayList<Integer> second)
    {
        for(int i=0; i<40; i++)
        {
            if( (first.get(i) < second.get(i)) )
            {
                first.set(i , first.get(i)+10 ); 
                first.set(i+1 , first.get(i+1)-1 );
            }
            result.add(i, first.get(i)-second.get(i));
        }
    }
    
    public void positiveOrNegative()
    {
        if((!isNegative(c1)) && (isNegative(c2)))
            resultIsNegative = true;
        if((isNegative(c1)) && (!isNegative(c2)))
            resultIsNegative = true;
        if((isNegative(c1)) && (isNegative(c2)))
            resultIsNegative = false;
        if((!isNegative(c1)) && (!isNegative(c2)))
            resultIsNegative = false;
    }
    public void product(ArrayList<Integer> first, ArrayList<Integer> second)
    {
        
        positiveOrNegative();
    }
    public void division(ArrayList<Integer> first, ArrayList<Integer> second)
    {
        positiveOrNegative();
    }
    
    public void comparison()
    {
        if((!isNegative(c1)) && (isNegative(c2)))
            System.out.println(" Number 1 is greater than number 2");
        if((isNegative(c1)) && (!isNegative(c2)))
            System.out.println(" Number 2 is greater than number 1");
        if((!isNegative(c1)) && (!isNegative(c2)))
        {
            if(isEqual())
                System.out.println("They are equal");
            if(!isEqual())
            {
                if(no1greaterThanNo2())
                    System.out.println("Number 1 is greater than number 2");
                if(!no1greaterThanNo2())
                    System.out.println("Number 2 is greater than number 1");
            }
        }     
        
        if((isNegative(c1)) && (isNegative(c2)))
        {
            if(isEqual())
                System.out.println("They are equal");
            if(!isEqual())
            {
                if(no1greaterThanNo2())
                    System.out.println("Number 2 is greater than number 1");
                if(!no1greaterThanNo2())
                    System.out.println("Number 1 is greater than number 2");
            }
        }   
    }
    public static void main(String[] args) 
    {
        HugeNums h = new HugeNums(); 
        h.convert(); 
        h.chooseOper(3);
        h.print();
        //h.comparison();   
    }
    
}
