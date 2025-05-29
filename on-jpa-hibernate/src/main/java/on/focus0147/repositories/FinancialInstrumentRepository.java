package on.focus0147.repositories;

import on.focus0147.entities.FinancialInstrument;

import java.util.Optional;

public interface FinancialInstrumentRepository {

    /**
     * Получить пользователей, которые предпочитают финансовый инструмент name
     */
    Optional<FinancialInstrument> findByIdWithUsers(Integer id);

}
