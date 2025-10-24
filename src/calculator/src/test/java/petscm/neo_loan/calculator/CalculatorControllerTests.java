package petscm.neo_loan.calculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import petscm.neo_loan.api_schemas.server.model.CalcOffersRequest;
import petscm.neo_loan.calculator.util.Copying;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CalculatorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private static final CalcOffersRequest calcOffersRequest = new CalcOffersRequest(
            BigDecimal.valueOf(200000),
            50,
            "Testfirst",
            "Testslast",
            "Testmiddle",
            "testmail@mail.te",
            LocalDate.of(2000, 10,10),
            "1234", "123456"
    );


    @ParameterizedTest
    @MethodSource({"provideCorrectValues"})
    void whenCorrectValuesThenCreated(CalcOffersRequest offersRequest) throws Exception {
        mockMvc.perform(post("/calculator/offers")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(offersRequest)))
                .andExpect(status().isCreated()).andDo(print());
    }

    @ParameterizedTest
    @MethodSource({"provideNotCorrectNames"})
    void whenNotCorrectValuesThenBadRequest(CalcOffersRequest loanStatement) throws Exception{
        mockMvc.perform(post("/calculator/offers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loanStatement))
                ).andExpect(status().isBadRequest())
                .andDo(print());
    }

    private static Stream<Arguments> provideCorrectValues(){
        var statement2 = Copying.copy(calcOffersRequest);
        statement2.setAmount(BigDecimal.valueOf(1400000000));
        statement2.setBirthdate(LocalDate.of(1922, 10,10));

        return Stream.of(Arguments.of(calcOffersRequest), Arguments.of(statement2));
    }

    private static Stream<Arguments> provideNotCorrectNames(){
        CalcOffersRequest statement1 = Copying.copy(calcOffersRequest);
        statement1.setLastName("");
        CalcOffersRequest statement12 = Copying.copy(calcOffersRequest);
        statement12.setLastName("f");
        CalcOffersRequest statement13 = Copying.copy(calcOffersRequest);
        statement13.setLastName(null);
        CalcOffersRequest statement14 = Copying.copy(calcOffersRequest);
        statement14.setLastName("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        CalcOffersRequest statement2 = Copying.copy(calcOffersRequest);
        statement2.setFirstName("");
        CalcOffersRequest statement22 = Copying.copy(calcOffersRequest);
        statement22.setFirstName("f");
        CalcOffersRequest statement23 = Copying.copy(calcOffersRequest);
        statement23.setFirstName(null);
        CalcOffersRequest statement24 = Copying.copy(calcOffersRequest);
        statement24.setLastName("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        CalcOffersRequest statement3 = Copying.copy(calcOffersRequest);
        statement3.setFirstName("");
        CalcOffersRequest statement32 = Copying.copy(calcOffersRequest);
        statement32.setFirstName("f");
        CalcOffersRequest statement33 = Copying.copy(calcOffersRequest);
        statement33.setFirstName(null);
        CalcOffersRequest statement34 = Copying.copy(calcOffersRequest);
        statement34.setLastName("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        return Stream.of(
                Arguments.of(statement1),
                Arguments.of(statement12),
                Arguments.of(statement13),
                Arguments.of(statement14),
                Arguments.of(statement2),
                Arguments.of(statement22),
                Arguments.of(statement23),
                Arguments.of(statement24),
                Arguments.of(statement3),
                Arguments.of(statement32),
                Arguments.of(statement33),
                Arguments.of(statement34)
        );
    }
}
