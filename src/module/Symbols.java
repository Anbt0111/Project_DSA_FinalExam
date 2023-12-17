package module;

public class Symbols {
    public int index;

    public Symbols(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getImagePath() { // lấy đường dẫn ảnh
        String s = "resources/images/symbols/" + index + ".png";
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        Symbols symbol = (Symbols) obj;
        return this.index == symbol.index;
    }

    @Override
    public String toString() {
        return "symbol" + index;
    }
}
