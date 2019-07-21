package app.entities;

public class Task {
    private int id;
    private String title;
    private String description;
    private boolean completeFlag;

    public Task() {
    }

    public Task(int id) {
        this.id = id;
    }

    public Task(int id, String title, String description, boolean completeFlag){
        this(title, description, completeFlag);
        this.id = id;
    }

    public Task(String title, String description, boolean completeFlag) {
        this.title = title;
        this.description = description;
        this.completeFlag = completeFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(boolean completeFlag) {
        this.completeFlag = completeFlag;
    }

}
