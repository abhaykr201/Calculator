package calculator;

import java.util.Stack;

public class EvaluateString1
{
    public static double evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();
 
         // Stack for numbers: 'values'
        Stack<Double> values = new Stack<Double>();
 
        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();
 
        for (int i = 0; i < tokens.length; i++)
        {
             // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;
 
            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));
                System.out.println(sbuf.toString());
            }
 
            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
 
            // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
 
            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                     tokens[i] == '*' || tokens[i] == '/' || tokens[i]=='^' || tokens[i]=='r')
            {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }
 
        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
        // Top of 'values' contains result, return it
        return values.pop();
    }
 
    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))   
            return false;
        if((op1=='r' || op1=='^'))
        	return false;
        else
            return true;
    }
 
    // A utility method to apply an operator 'op' on operands 'a' 
    // and 'b'. Return the result.
    public static Double applyOp(char op, Double b, Double a)
    {
        switch (op)
        {
        case '+':
            return new Double(a + b);
        case '-':
            return new Double(a - b);
        case '*':
            return new Double(a * b);
        case '^':
        	return new Double(Math.pow(a, b));
        case 'r':
        	return new Double(Math.pow(a, 1/b));
        case '/':
            if (b == 0)
                throw new
                UnsupportedOperationException("Cannot divide by zero");
            return new Double(a / b);
        }
        return 0.0;
    }
    
 
    // Driver method to test above methods
    public static void main(String[] args)
    {
       /* System.out.println(EvaluateString1.evaluate("( 10 + 5 + 2 - 3 ) *  54 + 69 / ( 19 + 4 )"));
        System.out.println(EvaluateString1.evaluate("100 * 2 + 12"));
        System.out.println(EvaluateString1.evaluate("100 * ( 2 + 12 )"));
        System.out.println(EvaluateString1.evaluate("100 * ( 2 + 12 ) / 14"));
        
        */
    	ExpCreate ob=new ExpCreate();
    	System.out.println(ob.fo("(10+x+2-3)*54+69/(19+4) for x=5"));
    }
}
class ExpCreate{
	double fo(String str) {
		String e_str="";
		String s[]=str.split("for");
		if(s.length>1) {
			String s1[]=s[1].split("=");
			String x="";
			String n="";
			for(int i=0;i<s1[0].length();i++) {
				if(s1[0].charAt(i)!=' ') {
					x=""+s1[0].charAt(i);
				}
			}
			for(int i=0;i<s1[1].length();i++) {
				if(s1[1].charAt(i)!=' ') {
					n=""+s1[1].charAt(i);
				}
			}
			String st[]=s[0].split("");
			for(int i=0;i<st.length;i++) {
				if(st[i].equals(x)) {
					st[i]=n;
				}
				e_str=e_str+st[i];
			}
			System.out.println("hh  " +e_str);
					
			
		}
		else {
			e_str =s[0];
		}
		return expo(e_str);	
			
		
		
	}
	double expo(String str) {
		String e_str="";
		
		String s[]=str.split("");
		String ch;
		for(int i=0;i<s.length;i++) {
			ch=s[i];
			if(ch.equals("(") || ch.equals(")") || ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/") || ch.equals("^") || ch.equals("r")) {
				e_str=e_str+" "+ch+" ";
			}
			else {
				e_str=e_str+ch;
			}
		}
		System.out.println(e_str);
		return EvaluateString1.evaluate(e_str);
		
	}
	
	
}