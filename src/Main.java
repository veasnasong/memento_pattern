//1. Memento Interface

public interface Memento {
    String getName();
    Date getSnapshotDate();
}

//2. Snapshot Class (Concrete Memento)

public class Snapshot implements Memento {
    private String text;
    private int cursorPos;
    private String selection;
    private String currentFont;
    private String styles;
    private Date snapshotDate;
    public Snapshot(String text, int cursorPos, String selection, String currentFont, String styles) {
        this.text = text;
        this.cursorPos = cursorPos;
        this.selection = selection;
        this.currentFont = currentFont;
        this.styles = styles;
        this.snapshotDate = new Date();
    }
    public String getText() {
        return text;
    }
    public int getCursorPos() {
        return cursorPos;
    }
    public String getSelection() {
        return selection;
    }
    public String getCurrentFont() {
        return currentFont;
    }
    public String getStyles() {
        return styles;
    }
    @Override
    public String getName() {
        return "Snapshot at " + snapshotDate.toString();
    }

    @Override
    public Date getSnapshotDate() {
        return snapshotDate;
    }
}

//3. Editor Class (Originator)

public class Editor {
    private String text;
    private int cursorPos;
    private String selection;
    private String currentFont;
    private String styles;

    public void setState(String text, int cursorPos, String selection, String currentFont, String styles) {
        this.text = text;
        this.cursorPos = cursorPos;
        this.selection = selection;
        this.currentFont = currentFont;
        this.styles = styles;
    }

    public Snapshot makeSnapshot() {
        return new Snapshot(text, cursorPos, selection, currentFont, styles);
    }

    public void restore(Memento memento) {
        if (memento instanceof Snapshot) {
            Snapshot snapshot = (Snapshot) memento;
            this.text = snapshot.getText();
            this.cursorPos = snapshot.getCursorPos();
            this.selection = snapshot.getSelection();
            this.currentFont = snapshot.getCurrentFont();
            this.styles = snapshot.getStyles();
        }
    }
    public void showState() {
        System.out.println("Editor State:");
        System.out.println("Text: " + text);
        System.out.println("Cursor Position: " + cursorPos);
        System.out.println("Selection: " + selection);
        System.out.println("Font: " + currentFont);
        System.out.println("Styles: " + styles);
    }
}

//4. History Class (Caretaker)

public class History {
    private List<Memento> history = new ArrayList<>();

    public void addSnapshot(Memento snapshot) {
        history.add(snapshot);
    }

    public Memento getSnapshot(int index) {
        if (index >= 0 && index < history.size()) {
            return history.get(index);
        }
        return null;
    }
    public void showHistory() {
        for (int i = 0; i < history.size(); i++) {
            System.out.println(i + ": " + history.get(i).getName());
        }
    }
}

//5. Command Class

public class Command {
    private History history;
    private Editor editor;

    public Command(Editor editor, History history) {
        this.editor = editor;
        this.history = history;
    }
    public void save() {
        Memento snapshot = editor.makeSnapshot();
        history.addSnapshot(snapshot);
        System.out.println("Snapshot saved.");
    }






