package framework.integrations.ftp.layoutReader.models.tokenizer;

import framework.integrations.ftp.layoutReader.InvalidLineValueException;
import framework.integrations.ftp.layoutReader.layout.FixedWidthField;
import framework.integrations.ftp.layoutReader.layout.LayoutFile;
import framework.logger.RegressionLogger;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.regex.Pattern.matches;
import static org.apache.commons.lang3.StringUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FixedWidthTokenizer implements ITokenizer {
    private LayoutFile.FixedWidthLayout.MasterDetail.Master masterModel;
    private LayoutFile.FixedWidthLayout.MasterDetail.Detail detailModel;
    private LayoutFile.FixedWidthLayout.Line lineModel;
    private RegressionLogger logger;

    public FixedWidthTokenizer(LayoutFile.FixedWidthLayout.MasterDetail.Master model, RegressionLogger logger) {
        this.masterModel = model;
        this.logger = logger;
    }

    public FixedWidthTokenizer(LayoutFile.FixedWidthLayout.MasterDetail.Detail model, RegressionLogger logger) {
        this.detailModel = model;
        this.logger = logger;
    }

    public FixedWidthTokenizer(LayoutFile.FixedWidthLayout.Line model, RegressionLogger logger) {
        this.lineModel = model;
        this.logger = logger;
    }

    @Override
    public boolean validateTokens(String line) {
        List<FixedWidthField> fields = new ArrayList<>();
        if (masterModel != null) {
            fields = masterModel.getFixedWidthField();
        }

        if (detailModel != null) {
            fields = detailModel.getFixedWidthField();
        }

        if (lineModel != null) {
            fields = lineModel.getFixedWidthField();
        }

        for (FixedWidthField field : fields) {
            try {
                String token = line.substring(0, field.getLength().intValue());

                // validating type
                switch (field.getType()) {
                    case DATE:
                        String dateFormat = field.getDateFormat();
                        if (StringUtils.isNotBlank(dateFormat)
                                && StringUtils.isNotEmpty(dateFormat)) {
                            LocalDate.parse(token, DateTimeFormatter.ofPattern(dateFormat));
                            break;
                        } else {
                            logger.fatal("Invalid Date format expression");
                            throw new InvalidLineValueException("Cannot convert: "+token+" to format: "+dateFormat);
                        }
                    case ALPHA:
                        assertThat(isAlphaSpace(token)).withFailMessage("Alpha value expected: "+token).isTrue();
                        break;
                    case BLANK:
                        assertThat(isAllBlank(token)).withFailMessage("Blank Value Expected: "+token).isTrue();
                        break;
                    case NUMERIC:
                        assertThat(isNumericSpace(token)).withFailMessage("Numeric Value Expected: "+token).isTrue();
                        break;
                    case ALPHA_NUMERIC:
                        assertThat(isAlphanumericSpace(token)).withFailMessage("Alpha Numeric Value expected: "+token).isTrue();
                        break;
                }

                // validating token if there is a regular expression
                if(field.getValidationPattern() != null){
                    assertThat(matches(field.getValidationPattern(), token)).withFailMessage("Input String: "+token+" fails validation against the given regular expression: "+field.getValidationPattern()).isTrue();
                }
            } catch (Exception e) {
                logger.fatal("Encountered Error: " + e.getLocalizedMessage());
                throw e;
            }

            // chopping string for next operation
            line = line.substring(field.getLength().intValue());
        }


        return true;
    }
}
