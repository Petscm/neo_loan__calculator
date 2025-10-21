package petscm.neo_loan.calculator.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Statement {
    private BigDecimal amount;
    private Integer term;
    private String firstName;
    private String lastName;
    private String middleName;

    @Getter
    public enum GenderEnum {
        MALE("MALE"),
        FEMALE("FEMALE"),
        NON_BINARY("NON_BINARY");

        private final String value;
        GenderEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private GenderEnum gender;
    private LocalDate birthdate;
    private String passportSeries;
    private String passportNumber;
    private LocalDate passportIssueDate;
    private String passportIssueBranch;

    @Getter
    public enum MaritalStatusEnum {
        MARRIED("MARRIED"),
        DIVORCED("DIVORCED"),
        SINGLE("SINGLE"),
        WIDOW_WIDOWER("WIDOW_WIDOWER");

        private final String value;
        MaritalStatusEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private MaritalStatusEnum maritalStatus;
    private Integer dependentAmount;
    private Employment employment;
    private String accountNumber;
    private Boolean isInsuranceEnabled;
    private Boolean isSalaryClient;
}

