package dandefors.tree;

/**
 *
 */
interface TreeTestData {

    /**
     * Input arrays from which to build trees.
     * Each array produces a unique red-black tree structure.
     */
    String[][] TREES = {

            {"A"},

            {"A", "B"},

            {"A", "B", "C"},

            {"A", "B", "C", "D"},
            {"A", "C", "D", "B"},

            {"A", "B", "C", "D", "E"},
            {"A", "C", "D", "B", "E"},

            {"A", "B", "C", "D", "E", "F"},
            {"A", "B", "C", "E", "F", "D"},
            {"A", "C", "D", "B", "E", "F"},

            {"A", "B", "C", "D", "E", "F", "G"},
            {"A", "B", "C", "E", "F", "D", "G"},
            {"A", "C", "D", "B", "E", "F", "G"},
            {"A", "C", "D", "B", "F", "G", "E"},

            {"A", "B", "C", "D", "E", "F", "G", "H"},
            {"A", "B", "C", "D", "E", "G", "H", "F"},
            {"A", "B", "C", "E", "F", "D", "G", "H"},
            {"A", "C", "D", "B", "E", "F", "G", "H"},
            {"A", "C", "D", "B", "F", "G", "E", "H"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "K"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "K"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "K"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "K"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "K"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "K"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "K"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "K"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "K"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "J"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "K", "L"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "K", "L"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I", "L"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "K", "L"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I", "L"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K", "L"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "K", "L", "J"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K", "L"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "K", "L", "J"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "K", "L"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "K", "L"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I", "L"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "K", "L"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I", "L"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G", "L"},
            {"A", "B", "C", "H", "I", "E", "K", "L", "F", "D", "G", "J"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "K", "L"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I", "L"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K", "L"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "K", "L", "J"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K", "L"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "K", "L", "J"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "K", "L"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "K", "L"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I", "L"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G", "L"},
            {"A", "C", "D", "B", "H", "I", "E", "K", "L", "F", "G", "J"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E", "L"},
            {"A", "C", "D", "B", "H", "I", "F", "K", "L", "G", "E", "J"},
            {"A", "C", "D", "B", "I", "J", "F", "K", "L", "G", "E", "H"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "J", "M"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "K", "L", "M"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "L", "M", "K"},
            {"A", "B", "C", "D", "E", "F", "G", "J", "K", "H", "L", "M", "I"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "K", "L", "M"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "L", "M", "K"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I", "L", "M"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "K", "L", "M"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "L", "M", "K"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I", "L", "M"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K", "L", "M"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "K", "L", "J", "M"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K", "L", "M"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "K", "L", "J", "M"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "K", "L", "M"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "L", "M", "K"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "K", "L", "M"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "L", "M", "K"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I", "L", "M"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "K", "L", "M"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "L", "M", "K"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I", "L", "M"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G", "L", "M"},
            {"A", "B", "C", "H", "I", "E", "K", "L", "F", "D", "G", "J", "M"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "K", "L", "M"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "L", "M", "K"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I", "L", "M"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K", "L", "M"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "K", "L", "J", "M"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K", "L", "M"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "K", "L", "J", "M"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "K", "L", "M"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "L", "M", "K"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "K", "L", "M"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "L", "M", "K"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I", "L", "M"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G", "L", "M"},
            {"A", "C", "D", "B", "H", "I", "E", "K", "L", "F", "G", "J", "M"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E", "L", "M"},
            {"A", "C", "D", "B", "H", "I", "F", "K", "L", "G", "E", "J", "M"},
            {"A", "C", "D", "B", "I", "J", "F", "K", "L", "G", "E", "H", "M"},
            {"A", "C", "D", "B", "I", "J", "F", "L", "M", "G", "E", "H", "K"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "M", "N", "L"},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "J", "M", "N"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "K", "L", "M", "N"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "L", "M", "K", "N"},
            {"A", "B", "C", "D", "E", "F", "G", "J", "K", "H", "L", "M", "I", "N"},
            {"A", "B", "C", "D", "E", "F", "G", "J", "K", "H", "M", "N", "I", "L"},
            {"A", "B", "C", "D", "E", "F", "G", "K", "L", "H", "M", "N", "I", "J"},
            {"A", "B", "C", "D", "E", "F", "G", "K", "L", "I", "M", "N", "J", "H"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "K", "L", "M", "N"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "L", "M", "K", "N"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I", "L", "M", "N"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I", "M", "N", "L"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "K", "L", "I", "M", "N", "J"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "K", "L", "M", "N"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "L", "M", "K", "N"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I", "L", "M", "N"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I", "M", "N", "L"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "K", "L", "I", "M", "N", "J"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K", "L", "M", "N"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K", "M", "N", "L"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "K", "L", "J", "M", "N"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K", "L", "M", "N"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K", "M", "N", "L"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "K", "L", "J", "M", "N"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "K", "L", "M", "N"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "L", "M", "K", "N"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "K", "L", "M", "N"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "L", "M", "K", "N"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I", "L", "M", "N"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I", "M", "N", "L"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "K", "L", "M", "N"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "L", "M", "K", "N"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I", "L", "M", "N"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I", "M", "N", "L"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G", "L", "M", "N"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G", "M", "N", "L"},
            {"A", "B", "C", "H", "I", "E", "K", "L", "F", "D", "G", "J", "M", "N"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "L", "M", "K", "N"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I", "L", "M", "N"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I", "M", "N", "L"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "K", "L", "I", "M", "N", "J"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K", "L", "M", "N"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K", "M", "N", "L"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "K", "L", "J", "M", "N"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K", "L", "M", "N"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K", "M", "N", "L"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "K", "L", "J", "M", "N"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "K", "L", "M", "N"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "L", "M", "K", "N"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "K", "L", "M", "N"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "L", "M", "K", "N"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I", "L", "M", "N"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I", "M", "N", "L"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G", "L", "M", "N"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G", "M", "N", "L"},
            {"A", "C", "D", "B", "H", "I", "E", "K", "L", "F", "G", "J", "M", "N"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E", "L", "M", "N"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E", "M", "N", "L"},
            {"A", "C", "D", "B", "H", "I", "F", "K", "L", "G", "E", "J", "M", "N"},
            {"A", "C", "D", "B", "I", "J", "F", "K", "L", "G", "E", "H", "M", "N"},
            {"A", "C", "D", "B", "I", "J", "F", "L", "M", "G", "E", "H", "K", "N"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "M", "N", "L", "O"},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "J", "M", "N", "O"},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "J", "N", "O", "M"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "K", "L", "M", "N", "O"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "K", "L", "N", "O", "M"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "L", "M", "K", "N", "O"},
            {"A", "B", "C", "D", "E", "F", "G", "J", "K", "H", "L", "M", "I", "N", "O"},
            {"A", "B", "C", "D", "E", "F", "G", "J", "K", "H", "M", "N", "I", "L", "O"},
            {"A", "B", "C", "D", "E", "F", "G", "K", "L", "H", "M", "N", "I", "J", "O"},
            {"A", "B", "C", "D", "E", "F", "G", "K", "L", "H", "N", "O", "I", "J", "M"},
            {"A", "B", "C", "D", "E", "F", "G", "K", "L", "I", "M", "N", "J", "H", "O"},
            {"A", "B", "C", "D", "E", "F", "G", "K", "L", "I", "N", "O", "J", "H", "M"},
            {"A", "B", "C", "D", "E", "F", "G", "L", "M", "I", "N", "O", "J", "H", "K"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "K", "L", "M", "N", "O"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "K", "L", "N", "O", "M"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "L", "M", "K", "N", "O"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I", "L", "M", "N", "O"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I", "M", "N", "L", "O"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "K", "L", "I", "M", "N", "J", "O"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "K", "L", "I", "N", "O", "J", "M"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "L", "M", "I", "N", "O", "J", "K"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "L", "M", "J", "N", "O", "K", "I"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "K", "L", "M", "N", "O"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "K", "L", "N", "O", "M"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "L", "M", "K", "N", "O"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I", "L", "M", "N", "O"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I", "M", "N", "L", "O"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "K", "L", "I", "M", "N", "J", "O"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "K", "L", "I", "N", "O", "J", "M"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "L", "M", "I", "N", "O", "J", "K"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "L", "M", "J", "N", "O", "K", "I"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K", "L", "M", "N", "O"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K", "M", "N", "L", "O"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "K", "L", "J", "M", "N", "O"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "K", "L", "J", "N", "O", "M"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "L", "M", "J", "N", "O", "K"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K", "L", "M", "N", "O"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K", "M", "N", "L", "O"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "K", "L", "J", "M", "N", "O"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "K", "L", "J", "N", "O", "M"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "L", "M", "J", "N", "O", "K"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "K", "L", "M", "N", "O"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "K", "L", "N", "O", "M"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "L", "M", "K", "N", "O"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "K", "L", "M", "N", "O"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "K", "L", "N", "O", "M"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "L", "M", "K", "N", "O"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I", "L", "M", "N", "O"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I", "M", "N", "L", "O"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "K", "L", "M", "N", "O"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "K", "L", "N", "O", "M"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "L", "M", "K", "N", "O"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I", "L", "M", "N", "O"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I", "M", "N", "L", "O"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G", "L", "M", "N", "O"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G", "M", "N", "L", "O"},
            {"A", "B", "C", "H", "I", "E", "K", "L", "F", "D", "G", "J", "M", "N", "O"},
            {"A", "B", "C", "H", "I", "E", "K", "L", "F", "D", "G", "J", "N", "O", "M"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "K", "L", "N", "O", "M"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "L", "M", "K", "N", "O"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I", "L", "M", "N", "O"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I", "M", "N", "L", "O"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "K", "L", "I", "M", "N", "J", "O"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "K", "L", "I", "N", "O", "J", "M"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "L", "M", "I", "N", "O", "J", "K"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "L", "M", "J", "N", "O", "K", "I"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K", "L", "M", "N", "O"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K", "M", "N", "L", "O"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "K", "L", "J", "M", "N", "O"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "K", "L", "J", "N", "O", "M"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "L", "M", "J", "N", "O", "K"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K", "L", "M", "N", "O"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K", "M", "N", "L", "O"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "K", "L", "J", "M", "N", "O"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "K", "L", "J", "N", "O", "M"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "L", "M", "J", "N", "O", "K"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "K", "L", "M", "N", "O"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "K", "L", "N", "O", "M"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "L", "M", "K", "N", "O"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "K", "L", "M", "N", "O"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "K", "L", "N", "O", "M"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "L", "M", "K", "N", "O"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I", "L", "M", "N", "O"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I", "M", "N", "L", "O"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G", "L", "M", "N", "O"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G", "M", "N", "L", "O"},
            {"A", "C", "D", "B", "H", "I", "E", "K", "L", "F", "G", "J", "M", "N", "O"},
            {"A", "C", "D", "B", "H", "I", "E", "K", "L", "F", "G", "J", "N", "O", "M"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E", "L", "M", "N", "O"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E", "M", "N", "L", "O"},
            {"A", "C", "D", "B", "H", "I", "F", "K", "L", "G", "E", "J", "M", "N", "O"},
            {"A", "C", "D", "B", "H", "I", "F", "K", "L", "G", "E", "J", "N", "O", "M"},
            {"A", "C", "D", "B", "I", "J", "F", "K", "L", "G", "E", "H", "M", "N", "O"},
            {"A", "C", "D", "B", "I", "J", "F", "K", "L", "G", "E", "H", "N", "O", "M"}

    };

    String[] CONST = splitIntoWords("We the People of the United States, in Order to form a " +
            "more perfect Union, establish Justice, insure domestic Tranquility, provide for the common defense, " +
            "promote the general Welfare, and secure the Blessings of Liberty to ourselves and our Posterity, do " +
            "ordain and establish this Constitution for the United States of America.");

    String[] GETTY = splitIntoWords("Four score and seven years ago our fathers brought forth " +
            "on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are " +
            "created equal. Now we are engaged in a great civil war, testing whether that nation, or any nation so " +
            "conceived and so dedicated, can long endure. We are met on a great battle-field of that war. We have " +
            "come to dedicate a portion of that field, as a final resting place for those who here gave their lives " +
            "that that nation might live. It is altogether fitting and proper that we should do this. But, in a larger " +
            "sense, we can not dedicate -- we can not consecrate -- we can not hallow -- this ground. The brave men, " +
            "living and dead, who struggled here, have consecrated it, far above our poor power to add or detract. " +
            "The world will little note, nor long remember what we say here, but it can never forget what they did " +
            "here. It is for us the living, rather, to be dedicated here to the unfinished work which they who fought " +
            "here have thus far so nobly advanced. It is rather for us to be here dedicated to the great task " +
            "remaining before us -- that from these honored dead we take increased devotion to that cause for which " +
            "they gave the last full measure of devotion -- that we here highly resolve that these dead shall not have " +
            "died in vain -- that this nation, under God, shall have a new birth of freedom -- and that government of " +
            "the people, by the people, for the people, shall not perish from the earth.");

    String[] LOREM = splitIntoWords("Lorem ipsum dolor sit amet, mauris ac lacus vel " +
            "pellentesque nulla augue, nec massa eget, pede ad odio libero, eget justo et urna ante pharetra, lectus " +
            "cras. Lorem et quo phasellus ridiculus sit pellentesque, risus explicabo morbi praesent purus mattis " +
            "vestibulum, nibh doloremque, ad in eros. Porta suspendisse, sed sodales praesent mattis libero voluptate " +
            "aliquam. Tristique sollicitudin erat, nec amet ut quam, molestie proin vehicula elit ipsum, gravida id " +
            "ultricies mollis sed lectus fugiat, nunc luctus urna. Quis mollis arcu est libero placerat et, elementum " +
            "elit venenatis. Vestibulum non vitae sed platea, quis blandit nunc tellus nulla in cras, elit tempor. " +
            "Nulla mattis non aliquam nullam, ac mi lectus viverra. Gravida dapibus lorem mauris metus phasellus, " +
            "mauris ultricies ante, lacinia lobortis euismod consequat mauris sit, erat adipiscing. Quis et adipiscing " +
            "vivamus, ipsum nam neque nunc libero, gravida ac proin duis ac, mauris aenean. Sit turpis duis vivamus, " +
            "sit curabitur mus tincidunt sapien volutpat, et elit elementum nec tempor. Eu placerat, libero etiam " +
            "tristique iaculis id felis. Ad est nec quis mi sed lorem. At minim ut dapibus congue felis magna, " +
            "pellentesque duis, wisi quis proin, risus lorem et. Consectetuer vestibulum ultrices lacus, at ac a id " +
            "curabitur, sodales ac mauris aenean pretium consequatur, ut id lacus. Lacinia quis, nulla nibh vel " +
            "sagittis tortor vivamus fermentum. At adipiscing feugiat aenean, turpis placerat imperdiet ligula nam est " +
            "sit, turpis viverra, ipsum porta porttitor tristique erat eget justo, ut curae nec turpis. Et arcu et in " +
            "nec pede tortor, quam condimentum maecenas, id at pharetra vitae viverra cras, odio lorem nullam enim " +
            "mauris conubia, proident eu sapien tristique. Posuere vestibulum leo eget curabitur diam, eu pede " +
            "ultricies magna vitae maecenas, aliquam posuere wisi at, hendrerit justo nunc, erat iaculis suspendisse " +
            "pede. Imperdiet aliquam ad convallis non suscipit. Maecenas at sed donec urna, sapien erat pharetra cras " +
            "facilisi elit. Diam nec est pretium, eu lectus integer litora turpis. Euismod lacus vehicula ipsum nec " +
            "laoreet, dolor ac mollis. Senectus sit malesuada lectus mauris mauris diam. Nam consequat, et ut at donec " +
            "mollis amet, mi et diam. Pede ac, suspendisse interdum hac nisl risus wisi. Elit sapien ut sollicitudin at, " +
            "tincidunt sollicitudin suspendisse wisi eleifend, aliquam eleifend dui lobortis ligula, nec nulla eu velit " +
            "eget. Et justo vel et justo, sed sapien nunc nec scelerisque nulla odio. Non metus pretium faucibus sunt " +
            "ac euismod, neque libero, erat amet pellentesque aliquet potenti tempus sed, vehicula fermentum wisi " +
            "tincidunt, nec lobortis egestas ut. Non neque et sed habitant sodales nulla. Fames pede ut et nec quisque, " +
            "mattis turpis metus justo et diam, ultrices vitae, aliquam urna lacus integer egestas tellus, fusce ut. " +
            "Dolor tincidunt purus laoreet amet sequi vestibulum. Amet luctus, justo luctus sed duis cursus vitae " +
            "aliquam, blandit mi justo inventore sit vivamus, suspendisse iaculis anim nam euismod, et tempus at. " +
            "Magnis doloremque. Et eget, nibh condimentum amet, pede velit dui eget, maecenas molestie elementum in, " +
            "euismod non id purus nonummy non diam. Ante sed vitae, etiam euismod viverra tincidunt. Etiam rutrum " +
            "suscipit tellus ac, nam ut.");

    static String[] splitIntoWords(String s) {
        return s.split("\\W+");
    }

}
