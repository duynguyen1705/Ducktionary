package CommandlineVer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Dictionary extends ArrayList<Word> {
    @Override
    public boolean add(Word word) {
        int index = Collections.binarySearch(this, word);
        if (index < 0) {
            index = -(index + 1);
        }
        super.add(index, word);
        return true;
    }
}
