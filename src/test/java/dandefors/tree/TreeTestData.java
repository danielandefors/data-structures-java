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

}