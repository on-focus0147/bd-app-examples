package on.focus0147.repositories;

import on.focus0147.entities.FinancialInstrument;

public interface FinancialInstrumentRepository {

    /**
     * Получить пользователей, которые предпочитают финансовый инструмент name
     */
    FinancialInstrument findByIdWithUsers(Integer id);

}
