module fr.nova.novascrape {

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;
    requires fr.brouillard.oss.cssfx;
    requires org.jsoup;
    requires org.json;

    opens fr.nova.novascrape to javafx.fxml;
    exports fr.nova.novascrape;
    exports fr.nova.novascrape.controller;
    opens fr.nova.novascrape.controller to javafx.fxml;
}
