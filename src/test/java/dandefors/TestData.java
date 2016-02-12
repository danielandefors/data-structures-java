package dandefors;

/**
 *
 */
public interface TestData {


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
