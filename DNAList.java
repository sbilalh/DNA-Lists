import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class DNAList {
    // protected LList<LList<Character>> sequenceArray;
    LList<Character>[] sequenceArray; // scuffed method?

    DNAList(int size) {
        // sequenceArray = new LList<LList<Character>>();

        sequenceArray = (LList<Character>[]) new LList[size]; // scuffed method?
    }

    private boolean charContains(char[] arr, char target) {
        for(char i : arr) {
            if(i == target) {
                return true;
            }
        }
        return false;
    }

    private boolean validateSequence(char[] sequence, String type) {
        char[] DNA = new char[]{'A','C','G','T'};
        char[] RNA = new char[]{'A','C','G','U'};

        for(char c : (type.equals("DNA") ? DNA : RNA)) {
            if(!charContains(sequence, c)) {
                return false;
            }
        }
        return true;
    }

    private LList<Character> createLListFromSequence(char[] sequence, String type) {
        if(!validateSequence(sequence, type)) {
            return null;
        }

        LList<Character> out = new LList<Character>();
        for(char c : sequence) {
            out.append(c);
        }
        return out;
    }

    void insert(int pos, String type, char[] sequence) {
        LList<Character> tmp = new LList<Character>();

        for(char c : sequence) {
            System.out.print(c + " ");
            tmp.append(c);
        }

        sequenceArray[pos] = tmp;  //TODO: does this mess up the references between assignments?
    }

    LList<Character> remove(int pos) {
        return null;
    }

    void print() {
        for(LList<Character> item : this.sequenceArray) {
            for(int i = 0; i < item.length(); i++) {
                item.moveToPos(i);
                System.out.println(item.getValue());
            }
        }
    }

    void print(int pos) {
        System.out.println(sequenceArray[pos].getValue());
    }

    void clip(int pos, int start, int end) {

    }

    void copy(int pos1, int pos2) {

    }

    void transcribe(int pos) {

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
                        
                        dList.insert(Integer.parseInt(cmd[1]), cmd[2], cmd[3].toCharArray());
                        
                        break;

                    case "remove":
                        if(cmd.length != 2) { throw new Error("invalid command"); }

                        break;

                    case "clip":
                        if(cmd.length != 4) { throw new Error("invalid command"); }

                        break;

                    case "copy":

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