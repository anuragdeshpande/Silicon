package framework.integrations.ftp.layoutReader.models.tokenizer;

import framework.integrations.ftp.layoutReader.layout.LayoutFile;

public class DelimitedTokenizer implements ITokenizer {
    LayoutFile.DelimitedLayout.DelimitedLine delimitedLineModel;
    public DelimitedTokenizer(LayoutFile.DelimitedLayout.DelimitedLine model) {
        this.delimitedLineModel = model;
    }

    @Override
    public boolean validateTokens(String line) {
        return false;
    }
}
