import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class DNAList extends Sequence{

    Sequence[] sequenceArray; // scuffed method?

    //creates array of type EMPTY with inputted size
    DNAList(int size) {
        sequenceArray = new Sequence[size];
        for(int i = 0; i < size; i ++) {
            Sequence fill = new Sequence(Type.EMPTY);
            sequenceArray[i] = fill;
        }
    }//

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
            System.out.println("No sequence to remove at specified position.");
        }
        else {
            sequenceArray[pos].clear();
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

    void clip(int pos, int start, int end) {
        //testing for valid input
        if (sequenceArray[pos].getType() == Type.EMPTY) {
            System.out.println("No sequence to print at specified position");
        } else if (start < 0) {System.out.println("Invalid start index");
        } else if (start > sequenceArray[pos].getLength()) {System.out.println("Start index out of bounds.");
        } else if (end > sequenceArray[pos].getLength()) {System.out.println("End index out of bounds.");
        //clipping //TODO: figure out logic for diff cases (start=end, end<start)
        } else {
            LList<Character> clip = new LList<>();
            for (int i = start; i <= end; i++) {// for edge case as has to be inclusive
                sequenceArray[pos].getList().moveToPos(i);
                clip.append(sequenceArray[pos].getList().getValue());
            }
            sequenceArray[pos].setSeq(clip);
        }


    }//

    void copy(int pos1, int pos2) {
        if (sequenceArray[pos1].getType() == Type.EMPTY) {
            System.out.println("No sequence to print at specified position");
        } else {
            sequenceArray[pos2].setSeq(sequenceArray[pos1]);
        }

    }

    void transcribe(int pos1) {

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
        System.out.println("Got args: " + args[0] + ", " + args[1]);

        DNAList dList = new DNAList(Integer.parseInt(args[0]));

        ArrayList<String[]> commands = readFile(args[1]);

        for(String[] cmd : commands) {
            System.out.println("cmd: " + cmd[0]);

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
