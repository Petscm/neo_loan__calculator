package petscm.neo_loan.calculator.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employment {
    @Getter
    public enum EmploymentStatusEnum {
        UNEMPLOYED("UNEMPLOYED"),
        SELF_EMPLOYED("SELF_EMPLOYED"),
        EMPLOYED("EMPLOYED"),
        BUSINESS_OWNER("BUSINESS_OWNER");

        private final String value;
        EmploymentStatusEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private EmploymentStatusEnum employmentStatus;
    private String employerINN;
    private BigDecimal salary;

    @Getter
    public enum PositionEnum {
        WORKER("WORKER"),
        MID_MANAGER("MID_MANAGER"),
        TOP_MANAGER("TOP_MANAGER"),
        OWNER("OWNER");

        private final String value;
        PositionEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private PositionEnum position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;
}