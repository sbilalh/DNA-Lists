/**
 * CSC 172 - Lab 4
 * Bilal Hussain (shussa11@u.rochester.edu)
 * Sammy Potter (spott14@u.rochester.edu)
 * 26 February 2022
 * See README.txt
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;

public class DNAList extends Sequence {

    Sequence[] sequenceArray; 

    //creates array of type EMPTY with inputted size
    DNAList(int size) {

        sequenceArray = new Sequence[size];
        for(int i = 0; i < size; i ++) {
            Sequence fill = new Sequence(Type.EMPTY);
            sequenceArray[i] = fill;
        }
    }

    //inserts sequence to position pos in the sequence array with enum type
    void insert(int pos, String type, String sequence) {

        boolean isValid = true;

        switch (type) {
            case "DNA":
                for (int i = 0; i < sequence.length(); i++) {
                    if (sequence.charAt(i) != 'A' && sequence.charAt(i) != 'C'
                            && sequence.charAt(i) != 'G' && sequence.charAt(i) != 'T') {
                        System.out.println("Error occurred while inserting");
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    LList<Character> seqList = new LList<>();
                    for (int i = 0; i < sequence.length(); i ++) {
                        seqList.append(sequence.charAt(i));
                    }
                    Sequence seq = new Sequence(Type.DNA, seqList);
                    sequenceArray[pos] = seq;
                }
                break;

            case "RNA":
                for (int i = 0; i < sequence.length(); i++) {
                    if (sequence.charAt(i) != 'A' && sequence.charAt(i) != 'C'
                            && sequence.charAt(i) != 'G' && sequence.charAt(i) != 'U') {
                        System.out.println("Error occurred while inserting");
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    LList<Character> seqList = new LList<>();
                    for (int i = 0; i < sequence.length(); i ++) {
                        seqList.append(sequence.charAt(i));
                    }
                    Sequence seq = new Sequence(Type.RNA, seqList);
                    sequenceArray[pos] = seq;
                }
                break;
        }
    }

    //removes sequence at specified point
    void remove(int pos) {

        if (sequenceArray[pos].getType() == Type.EMPTY) {
            System.out.println("No sequence to remove at specified position");
        }
        else {
            sequenceArray[pos].getList().clear();
            sequenceArray[pos].setType(Type.EMPTY);
        }
    }

    //prints all sequences
    void print() {

        for(int i = 0; i < sequenceArray.length; i++) {
            if (sequenceArray[i].getType() != Type.EMPTY)
                System.out.println(i + "\t" + sequenceArray[i].getType() + "\t" + sequenceArray[i].getSeq());
        }
    }

    //prints sequence at specified point
    void print(int pos) {

        if (sequenceArray[pos].getType() == Type.EMPTY) {
            System.out.println("No sequence to print at specified position");
        } else {System.out.println(sequenceArray[pos].getType() + "\t" + sequenceArray[pos].getSeq());}
    }

    //replaces the sequence at position pos with a clipped version of the sequence
    void clip(int pos, int start, int end) {

        if (sequenceArray[pos].getType() == Type.EMPTY) {
            System.out.println("No sequence to clip at specified position");
        } else if (start < 0) {System.out.println("Invalid start index");
        } else if (start > sequenceArray[pos].getLength()) {System.out.println("Start index out of bounds");
        } else if (end > sequenceArray[pos].getLength()) {System.out.println("End index is out of bounds");
        } else {
            LList<Character> clip = new LList<>();
            for (int i = start; i <= end; i++) {
                sequenceArray[pos].getList().moveToPos(i);
                clip.append(sequenceArray[pos].getList().getValue());
            }
            sequenceArray[pos].setList(clip);
        }
    }

    //copies the sequence at pos1 and sets it to pos2
    void copy(int pos1, int pos2) {

        if (sequenceArray[pos1].getType() == Type.EMPTY) {
            System.out.println("No sequence to copy at specified position");
        } else {
            sequenceArray[pos2].setList(sequenceArray[pos1].getList());
        }

    }

    //converts DNA sequence at pos1 to RNA sequence
    void transcribe(int pos1) {

        if (sequenceArray[pos1].getType() == Type.RNA) {
            System.out.println("Cannot transcribe RNA");
        } else if (sequenceArray[pos1].getType() == Type.EMPTY) {
            System.out.println("No sequence to transcribe at specified position");
        } else {
            sequenceArray[pos1].setType(Type.RNA);
            
            Map<Character, Character> trMap = Map.of(
                'A','U',
                'T','A',
                'C','G',
                'G','C'
            );

            for (int i = 0; i < sequenceArray[pos1].getLength(); i++) {
                sequenceArray[pos1].getList().moveToPos(i);
                Character currentChar = sequenceArray[pos1].getSeq().charAt(i);

                sequenceArray[pos1].getList().remove();
                sequenceArray[pos1].getList().insert(trMap.get(currentChar));
            }
        }
    }

    private static ArrayList<String[]> readFile(String filePath) {

        ArrayList<String[]> out = new ArrayList<String[]>();

        try {
            File file = new File(filePath);
            Scanner scnr = new Scanner(file);
            while (scnr.hasNextLine()) {
                out.add(scnr.nextLine().split("[ \t]+"));
            }
            scnr.close();
        } catch (FileNotFoundException e) {
            System.out.printf("Error: missing file '%s'\n", filePath);
        }

        return out;

    }

    public static void main(String[] args) {

        DNAList dList = new DNAList(Integer.parseInt(args[0]));
        ArrayList<String[]> commands = readFile(args[1]);

        for(String[] cmd : commands) {
            try {
                switch(cmd[0]) {
                    case "insert":
                        if(cmd.length != 4) { throw new Error("invalid command"); }
                        
                        dList.insert(Integer.parseInt(cmd[1]), cmd[2], cmd[3]);
                        
                        break;

                    case "remove":
                        if(cmd.length != 2) { throw new Error("invalid command"); }

                        dList.remove(Integer.parseInt(cmd[1]));

                        break;

                    case "clip":
                        if(cmd.length != 4) { throw new Error("invalid command"); }

                        dList.clip(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]));

                        break;

                    case "copy":
                        if(cmd.length != 3) { throw new Error("invalid command"); }

                        dList.copy(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));

                        break;

                    case "transcribe":
                        if(cmd.length != 2) { throw new Error("invalid command"); }

                        dList.transcribe(Integer.parseInt(cmd[1]));

                        break;

                    case "print":
                        if(cmd.length == 1) {
                            dList.print();
                        } else if(cmd.length == 2) {
                            dList.print(Integer.parseInt(cmd[1]));
                        } else {
                            throw new Error("invalid command");
                        }
                        break;

                    default:
                        System.out.printf("command '%s' not recognized\n", cmd[0]);
                        break;
                }
            } catch(Error e) {
                System.out.println(e);
            }
        }
    }
}
