package CommandlineVer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTree {
    private final Map<Character, BinaryTree> children;
    private String content;
    private boolean terminal;

    public BinaryTree() {
        content = "";
        children = new HashMap<>();
        terminal = false;
    }

    private BinaryTree(String content) {
        this.content = content;
        children = new HashMap<>();
        terminal = false;
    }

    public void insert(String word) {
        if (word == null) throw new IllegalArgumentException("Null entries are not valid.");
        BinaryTree node = this;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new BinaryTree(node.content + c));
            node = node.children.get(c);
        }
        node.terminal = true;
    }

    public List<String> autoComplete(String prefix) {
        BinaryTree trieNode = this;
        for (char c : prefix.toCharArray()) {
            trieNode = trieNode.children.get(c);
            if (trieNode == null) return new ArrayList<>();
        }
        return trieNode.allPrefixes();
    }

    private List<String> allPrefixes() {
        List<String> results = new ArrayList<>();
        if (terminal) {
            results.add(content);
        }
        for (BinaryTree child : children.values()) {
            results.addAll(child.allPrefixes());
        }
        return results;
    }
}
