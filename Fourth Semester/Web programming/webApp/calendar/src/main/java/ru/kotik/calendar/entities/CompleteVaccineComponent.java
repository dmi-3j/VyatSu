package ru.kotik.calendar.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "completecomponents")
public class CompleteVaccineComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date vaccinationdate;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private MedicalOrganization medicalOrganization;
    @ManyToOne
    @JoinColumn(name = "vaccination_id") // имя столбца, который ссылается на таблицу vaccinations
    private Vaccination vaccination;

    @ManyToOne
    @JoinColumn(name = "vaccine_component_id") // имя столбца, который ссылается на таблицу vaccinecomponents
    private VaccineComponent vaccineComponent;

}
