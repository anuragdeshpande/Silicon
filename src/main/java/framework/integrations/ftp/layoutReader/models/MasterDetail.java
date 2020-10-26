package framework.integrations.ftp.layoutReader.models;

import framework.integrations.ftp.layoutReader.layout.LayoutFile;
import framework.integrations.ftp.layoutReader.models.tokenizer.FixedWidthTokenizer;
import framework.logger.RegressionLogger;
import org.junit.Assert;

import java.util.LinkedList;

public class MasterDetail {
    private String _masterLine = null;
    private final LinkedList<String> _detailLines;
    private final LayoutFile.FixedWidthLayout.MasterDetail.Detail _detailLayout;
    private final LayoutFile.FixedWidthLayout.MasterDetail.Master _masterLayout;
    private String currentDetailLine;
    private final RegressionLogger logger;

    public MasterDetail(RegressionLogger logger, LayoutFile.FixedWidthLayout.MasterDetail.Master masterLayout, LayoutFile.FixedWidthLayout.MasterDetail.Detail detailLayout){
        this._masterLayout = masterLayout;
        this._detailLayout = detailLayout;
        this._detailLines = new LinkedList<>();
        this.logger = logger;
    }

    public MasterDetail addMasterLine(String line){
        this._masterLine = line;
        return this;
    }

    public MasterDetail addDetailLine(String line){
        if(_masterLine == null){
            Assert.fail("Cannot add Detail Line without Master Line: Invalid File");
            logger.fail("Cannot add Detail Line without master Line");
        } else {
            this._detailLines.add(line);
            this.currentDetailLine = line;
        }
        return this;
    }

    public boolean hasMasterLine(){
        return this._masterLine != null;
    }

    public boolean hasDetailLines(){
        return this._detailLines.size() > 0;
    }

    public boolean isMasterValid(){
        FixedWidthTokenizer tokenizer = new FixedWidthTokenizer(_masterLayout, logger);
        return tokenizer.validateTokens(_masterLine);
    }

    public boolean isDetailValid(){
        FixedWidthTokenizer tokenizer = new FixedWidthTokenizer(_detailLayout, logger);
        return tokenizer.validateTokens(currentDetailLine);
    }
}
