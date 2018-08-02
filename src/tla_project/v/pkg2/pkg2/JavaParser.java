/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tla_project.v.pkg2.pkg2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import javafx.scene.paint.Color;

/**
 *
 * @author soheilchangizi && alirezakavian
 */
public class JavaParser {

    /**
     *
     * defining: *CYK Table: 2d Array of ArrayLists that holds the left variable
     * of the rules if the cyk algorithm accepts it *NonTerminal: a HashMap
     * holds the rules which the right hand of it takes nonTerminal *Terminal: a
     * HashMap holds the rules which the right hand of it takes Terminals
     * *stack: to specify the syntax of "()" and "{}" and "[]"
     *
     */
    public static ArrayList<String>[][] CYKtable;
    public static HashMap<ArrayList<String>, String> nonTerminals;
    public static HashMap<ArrayList<String>, String> Terminals;
    public static Stack<String> stack = new Stack<>();

    //The CFG (better to say CNF) of the syntax parsing
    public static void initGrammer() {

        nonTerminals = new HashMap<>();
        Terminals = new HashMap<>();
        ArrayList<String> temp = new ArrayList<>();

        //Terminals
        temp.add("(");
        Terminals.put(temp, "[Prn]");

        temp = new ArrayList<>();
        temp.add(")");
        Terminals.put(temp, "[Bprn]");

        temp = new ArrayList<>();
        temp.add("{");
        Terminals.put(temp, "[Accol]");

        temp = new ArrayList<>();
        temp.add("+");
        Terminals.put(temp, "[Plus]");

        temp = new ArrayList<>();
        temp.add("-");
        Terminals.put(temp, "[Minus]");

        temp = new ArrayList<>();
        temp.add("*");
        Terminals.put(temp, "[Pro]");

        temp = new ArrayList<>();
        temp.add("/");
        Terminals.put(temp, "[Div]");

        temp = new ArrayList<>();
        temp.add("%");
        Terminals.put(temp, "[Mod]");

        temp = new ArrayList<>();
        temp.add("==");
        Terminals.put(temp, "[Eq]");

        temp = new ArrayList<>();
        temp.add("!=");
        Terminals.put(temp, "[Neq]");

        temp = new ArrayList<>();
        temp.add(">");
        Terminals.put(temp, "[Bigger]");

        temp = new ArrayList<>();
        temp.add("<");
        Terminals.put(temp, "[Smaller]");

        temp = new ArrayList<>();
        temp.add("&&");
        Terminals.put(temp, "[And]");

        temp = new ArrayList<>();
        temp.add("||");
        Terminals.put(temp, "[Or]");

        temp = new ArrayList<>();
        temp.add(";");
        Terminals.put(temp, "[Sem]");

        temp = new ArrayList<>();
        temp.add(".");
        Terminals.put(temp, "[Dot]");

        temp = new ArrayList<>();
        temp.add("while");
        Terminals.put(temp, "[While_Loop]");

        temp = new ArrayList<>();
        temp.add("for");
        Terminals.put(temp, "[For_Loop]");

        temp = new ArrayList<>();
        temp.add("if");
        Terminals.put(temp, "[If_Conditional]");

        temp = new ArrayList<>();
        temp.add("else");
        Terminals.put(temp, "[Else_Conditional]");

        temp = new ArrayList<>();
        temp.add("=");
        Terminals.put(temp, "[Assign_Symbol]");

        temp = new ArrayList<>();
        temp.add("int");
        temp.add("float");
        temp.add("char");
        temp.add("double");
        Terminals.put(temp, "[DataType]");

        temp = new ArrayList<>();
        temp.add("a");
        temp.add("b");
        temp.add("c");
        temp.add("d");
        temp.add("e");
        temp.add("f");
        temp.add("g");
        temp.add("h");
        temp.add("i");
        temp.add("j");
        temp.add("k");
        temp.add("l");
        temp.add("m");
        temp.add("n");
        temp.add("o");
        temp.add("p");
        temp.add("q");
        temp.add("r");
        temp.add("s");
        temp.add("t");
        temp.add("u");
        temp.add("v");
        temp.add("w");
        temp.add("x");
        temp.add("y");
        temp.add("z");
        Terminals.put(temp, "[Name]");

        temp = new ArrayList<>();
        temp.add("0");
        temp.add("1");
        temp.add("2");
        temp.add("3");
        temp.add("4");
        temp.add("5");
        temp.add("6");
        temp.add("7");
        temp.add("8");
        temp.add("9");
        Terminals.put(temp, "[Numb]");

        temp = new ArrayList<>();
        temp.add("a");
        temp.add("b");
        temp.add("c");
        temp.add("d");
        temp.add("e");
        temp.add("f");
        temp.add("g");
        temp.add("h");
        temp.add("i");
        temp.add("j");
        temp.add("k");
        temp.add("l");
        temp.add("m");
        temp.add("n");
        temp.add("o");
        temp.add("p");
        temp.add("q");
        temp.add("r");
        temp.add("s");
        temp.add("t");
        temp.add("u");
        temp.add("v");
        temp.add("w");
        temp.add("x");
        temp.add("y");
        temp.add("z");

        temp.add("0");
        temp.add("1");
        temp.add("2");
        temp.add("3");
        temp.add("4");
        temp.add("5");
        temp.add("6");
        temp.add("7");
        temp.add("8");
        temp.add("9");
        Terminals.put(temp, "[Exp]");

        temp = new ArrayList<>();
        temp.add("}");
        temp.add("//");
        Terminals.put(temp, "[SP]");

        temp = new ArrayList<>();
        temp.add("//");
        Terminals.put(temp, "[Comm]");

        //nonterminals
        temp = new ArrayList<>();
        temp.add("[S] [Sem]");
        temp.add("[While_Loop] [U8]");
        temp.add("[For_Loop] [U9]");
        temp.add("[If_Conditional] [U18]");
        temp.add("[Else_Conditional] [Accol]");
        temp.add("[Comm] [Exp]");
        nonTerminals.put(temp, "[SP]");

        temp = new ArrayList<>();
        temp.add("[DataType] [Name]");
        temp.add("[Declare] [U1]");
        temp.add("[Name] [U1]");
        nonTerminals.put(temp, "[S]");

        temp = new ArrayList<>();
        temp.add("[Declare] [U1]");
        temp.add("[Name] [U1]");
        nonTerminals.put(temp, "[Init]");

        temp = new ArrayList<>();
        temp.add("[DataType] [Name]");
        nonTerminals.put(temp, "[Declare]");

        temp = new ArrayList<>();
        temp.add("[Exp] [U2]");
        temp.add("[Exp] [U3]");
        temp.add("[Exp] [U4]");
        temp.add("[Exp] [U5]");
        temp.add("[Exp] [U6]");
        nonTerminals.put(temp, "[Exp]");

        temp = new ArrayList<>();
        temp.add("[Exp] [U21]");
        temp.add("[Exp] [U22]");
        temp.add("[Exp] [U23]");
        temp.add("[Exp] [U24]");
        temp.add("[Condition] [V1]");
        temp.add("[Condition] [V2]");
        nonTerminals.put(temp, "[Condition]");

        temp = new ArrayList<>();
        temp.add("[And] [Condition]");
        nonTerminals.put(temp, "[V1]");

        temp = new ArrayList<>();
        temp.add("[Or] [Condition]");
        nonTerminals.put(temp, "[V2]");

        temp = new ArrayList<>();
        temp.add("[Name] [U1]");
        nonTerminals.put(temp, "[Assign]");

        temp = new ArrayList<>();
        temp.add("[Assign_Symbol] [Exp]");
        nonTerminals.put(temp, "[U1]");

        temp = new ArrayList<>();
        temp.add("[Plus] [Exp]");
        nonTerminals.put(temp, "[U2]");

        temp = new ArrayList<>();
        temp.add("[Minus] [Exp]");
        nonTerminals.put(temp, "[U3]");

        temp = new ArrayList<>();
        temp.add("[Pro] [Exp]");
        nonTerminals.put(temp, "[U4]");

        temp = new ArrayList<>();
        temp.add("[Div] [Exp]");
        nonTerminals.put(temp, "[U5]");

        temp = new ArrayList<>();
        temp.add("[Mod] [Exp]");
        nonTerminals.put(temp, "[U6]");

        temp = new ArrayList<>();
        temp.add("[U19] [U20]");
        nonTerminals.put(temp, "[U8]");

        temp = new ArrayList<>();
        temp.add("[U12] [U13]");
        nonTerminals.put(temp, "[U9]");

        temp = new ArrayList<>();
        temp.add("[U14] [U15]");
        nonTerminals.put(temp, "[U12]");

        temp = new ArrayList<>();
        temp.add("[U16] [U20]");
        nonTerminals.put(temp, "[U13]");

        temp = new ArrayList<>();
        temp.add("[Prn] [Init]");
        nonTerminals.put(temp, "[U14]");

        temp = new ArrayList<>();
        temp.add("[Sem] [Condition]");
        nonTerminals.put(temp, "[U15]");

        temp = new ArrayList<>();
        temp.add("[Sem] [Assign]");
        nonTerminals.put(temp, "[U16]");

        temp = new ArrayList<>();
        temp.add("[U19] [U20]");
        nonTerminals.put(temp, "[U18]");

        temp = new ArrayList<>();
        temp.add("[Prn] [Condition]");
        nonTerminals.put(temp, "[U19]");

        temp = new ArrayList<>();
        temp.add("[Bprn] [Accol]");
        nonTerminals.put(temp, "[U20]");

        temp = new ArrayList<>();
        temp.add("[Eq] [Exp]");
        nonTerminals.put(temp, "[U21]");

        temp = new ArrayList<>();
        temp.add("[Neq] [Exp]");
        nonTerminals.put(temp, "[U22]");

        temp = new ArrayList<>();
        temp.add("[Bigger] [Exp]");
        nonTerminals.put(temp, "[U23]");

        temp = new ArrayList<>();
        temp.add("[Smaller] [Exp]");
        nonTerminals.put(temp, "[U24]");

    }

    public static boolean checkSyntaxCYK(String in) {

        //to split the line given from file into an array of strings
        String[] splited = in.split("\\s+");

        for (char c : in.toCharArray()) {
            String str = String.valueOf(c);

            //checking syntax of '()','{}','[]'
            String top;
            switch (str) {
                case "{":
                case "[":
                case "(":
                    stack.push(str);
                    continue;
                ////
                case ")":
                    if (!stack.empty()) {
                        top = stack.pop();
                        if (!"(".equals(top)) {
                            if ("[".equals(top)) {
                                System.out.print("\']\' expected | ");
                            } else {
                                System.out.print("\'}\' expected | ");
                            }
                        }
                    } else {
                        System.out.print("illegal start of expression | ");
                    }
                    continue;
                case "]":
                    if (!stack.empty()) {
                        top = stack.pop();
                        if (!"[".equals(top)) {
                            if ("(".equals(top)) {
                                System.out.print("\')\' expected | ");
                            } else {
                                System.out.print("\'}\' expected | ");
                            }
                        }
                    } else {
                        System.out.print("illegal start of expression | ");
                    }
                    continue;
                case "}":
                    if (!stack.empty()) {
                        top = stack.pop();
                        if (!"{".equals(top)) {
                            if ("(".equals(top)) {
                                System.out.print("\')\' expected | ");
                            } else {
                                System.out.print("\']\' expected | ");
                            }
                        }
                    } else {
                        System.out.print("illegal start of expression | ");
                    }
                    continue;
            }
        }

        //memory allocation of the cykTable array of objects
        //size : (Number of words in line)*(Number of words in line)
        CYKtable = new ArrayList[splited.length][];
        for (int i = 0; i < splited.length; i++) {
            CYKtable[i] = new ArrayList[splited.length];
            for (int j = 0; j < splited.length; j++) {
                CYKtable[i][j] = new ArrayList< String>();
            }
        }

        Set< ArrayList<String>> Tkeys = Terminals.keySet();
        //System.out.println(Tkeys);
        /*
         the booleans are used to check if the word is of Name Or Numb Or Exp 
         */
        boolean checkName = true;
        boolean checkNumber = true;
        ArrayList<String> NameKey = new ArrayList<>(); //a|b|c|..
        ArrayList<String> NumbKey = new ArrayList<>();//1|2|3|..
        ArrayList<String> ExpKey = new ArrayList<>();//both of the Name and Numb

        for (int i = 0; i < splited.length; i++) {
            for (ArrayList<String> key : Tkeys) {
                if (key.contains(splited[i])) {
                    CYKtable[i][i].add(Terminals.get(key));
                }
                /*
                 initialize the reference of the *key
                 */
                if (Terminals.get(key).equals("[Name]")) {
                    NameKey = key;
                } else if (Terminals.get(key).equals("[Numb]")) {
                    NumbKey = key;
                } else if (Terminals.get(key).equals("[Exp]")) {
                    ExpKey = key;
                }
            }

            //checking
            if (splited[i].contains("int")
                    || splited[i].contains("int")
                    || splited[i].contains("char")
                    || splited[i].contains("float")
                    || splited[i].contains("double")
                    || splited[i].contains("if")
                    || splited[i].contains("while")
                    || splited[i].contains("else")
                    || splited[i].contains("for"))
            {
                continue;
            }
            if (NameKey.contains("" + splited[i].charAt(0))) {
                checkNumber = false;
                for (int j = 1; j < splited[i].length(); j++) {
                    if (!(NameKey.contains(splited[i].charAt(j) + "")
                            || (NumbKey.contains(splited[i].charAt(j) + "")))) {
                        checkName = false;
                        j = splited.length;
                    }
                }
                //adding the name string to Terminals
                if (checkName && !CYKtable[i][i].contains(Terminals.get(NameKey))) {
                    CYKtable[i][i].add(Terminals.get(NameKey));
                    CYKtable[i][i].add(Terminals.get(ExpKey));
                }
            } else if (NumbKey.contains("" + splited[i].charAt(0))) {
                checkName = false;
                for (int j = 1; j < splited[i].length(); j++) {
                    if (!(NumbKey.contains(splited[i].charAt(j) + ""))) {
                        checkNumber = false;
                        j = splited.length;
                    }

                }
                //adding the Numb string to Terminals
                if (checkNumber && !CYKtable[i][i].contains(Terminals.get(NumbKey))) {
                    CYKtable[i][i].add(Terminals.get(NumbKey));
                    CYKtable[i][i].add(Terminals.get(ExpKey));
                }
            }
            checkName = true;
            checkNumber = true;
        }

        /*
         the main process of CYK algorithm
         */
        Set< ArrayList<String>> nTkeys = nonTerminals.keySet();
        //System.out.println(nTkeys);
        String[] split;
        for (int l = 2; l <= splited.length; l++) {
            for (int i = 0; i <= splited.length - l; i++) {
                int j = i + l - 1;
                for (int k = i; k <= j - 1; k++) {
                    for (ArrayList<String> key : nTkeys) {
                        for (String key1 : key) {
                            split = key1.split("\\s+");
                            if (CYKtable[i][k].contains((split[0]))
                                    && CYKtable[k + 1][j].contains(split[1])) {
                                CYKtable[i][j].add(nonTerminals.get(key));
                            }
                        }
                    }
                }
            }
        }

        ////////
        ///////checking if the array in position <0,length-1> contains the START VARIABLE
        if (CYKtable[0][splited.length - 1].contains("[SP]")) {
            return true;
        }
        return false;
    }

    //MAIN FUNCTION
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        File file;
        System.out.print("Enter the file path: ");
        String s = in.nextLine();
        file = new File(s);
        if (!file.exists()) {
            System.out.println((char) 27 + "[33mthe file does not exists!");
            System.exit(0);
        }
        System.out.println((char) 27 + "[46;31mSyntax Errors:" + (char) 27 + "[0m");
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String line = bf.readLine(); //reading first line

        initGrammer();

        int lineCounter = 1;

        //reading each line in a loop and process them by CYK algorithm separately
        while (line != null) {
            System.out.format((char) 27 + "[34mIn Line %-2d:> " + (char) 27 + "[30m", lineCounter);
            if (!checkSyntaxCYK(line)) {
                System.out.println((char) 27 + "[31mWrong " + (char) 27 + "[30msyntax format");
            } else {
                System.out.println((char) 27 + "[32mCorrect" + (char) 27 + "[30m syntax format");
            }
            line = bf.readLine();
            lineCounter++;
        }
    }

}
