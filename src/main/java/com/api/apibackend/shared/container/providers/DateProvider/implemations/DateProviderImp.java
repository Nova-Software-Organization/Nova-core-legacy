/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.shared.container.providers.DateProvider.implemations;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.api.apibackend.shared.container.providers.DateProvider.IDateProvider;

public class DateProviderImp implements IDateProvider {
    
    private Date date;

    public Optional<Date> compareIsBefore(Date startDate, Date endDate) {
        return Optional.ofNullable(startDate).filter(date -> endDate != null && date.before(endDate));
    }

    public List<Date> filterDatesWithinPeriod(List<Date> dates, Date startDate, Date endDate) {
        if (dates == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        return dates.stream()
                .filter(date -> !date.before(startDate) && !date.after(endDate))
                .collect(Collectors.toList());
    }
}
