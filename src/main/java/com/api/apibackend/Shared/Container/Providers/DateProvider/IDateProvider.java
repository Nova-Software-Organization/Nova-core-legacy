/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Shared.Container.Providers.DateProvider;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IDateProvider {
    public Optional<Date> compareIsBefore(Date startDate, Date endDate);
    public List<Date> filterDatesWithinPeriod(List<Date> dates, Date startDate, Date endDate);
}
