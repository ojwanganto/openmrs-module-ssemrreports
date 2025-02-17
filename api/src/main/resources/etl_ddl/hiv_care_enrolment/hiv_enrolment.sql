-- Drop the table if exists --
DROP TABLE IF EXISTS flat_encounter_hiv_care_enrolment;

-- Create table hiv_care_enrolment --

CREATE TABLE flat_encounter_hiv_care_enrolment
(
    date_first_tested_positive                        DATE,
    place_first_tested_positive                       VARCHAR(255),
    entry_point                                       VARCHAR(255),
    date_of_hiv_retesting_before_art                  DATE,
    place_of_hiv_retesting_before_art                 VARCHAR(255),
    transferred_in_on_art_from_another_treatment_site VARCHAR(255),
    name_of_previous_clinic                           VARCHAR(255),
    date_tranferred_in                                DATE,
    literate                                          VARCHAR(255),
    alcohol_intake                                    VARCHAR(255),
    drug_allergies                                    VARCHAR(255),
    marital_status                                    VARCHAR(255),
    occupation                                        VARCHAR(255),
    family_member_names                               VARCHAR(255),
    family_member_unique_number_if_in_care            VARCHAR(255),
    family_member_hiv_status                          VARCHAR(255),
    is_family_member_in_art_care                      VARCHAR(255),
    family_member_art_number                          VARCHAR(255),
    family_member_age                                 INT(15),
    family_member_sex                                 VARCHAR(255),
    were_arvs_received                                VARCHAR(255),
    if_yes_arvs_received                              VARCHAR(255),
    place_where_arvs_were_received                    VARCHAR(255),     
    drugs_and_duration_start_date                     DATE,
    drugs_and_duration_stop_date                      DATE,
    ctx_or_dapstone_start_date                        DATE,
    milestones_at_start_of_art                        VARCHAR(255),
    who_stage                                         VARCHAR(255),
    weight_clinical_milestone                         INT(15),
    height_cm                                         INT(15),
    date_clinical_milestone                           DATE,
    cd4                                               INT(15),
    art_readiness_confirmed_by                        VARCHAR(255),
    art_readiness_confirmation_date                   DATE,
    initial_art_regimen_for_adult                     VARCHAR(255),
    initial_art_regimen_for_children                  VARCHAR(255),
    cohort                                            DATE,
    art_regimen                                       VARCHAR(255),
    hivtc_new_regimen                                 VARCHAR(255),
    art_first_line_switch_date                        DATE,
    substitution_regimen_for_adults_first_line        VARCHAR(255),
    child_regimen_first_line                          VARCHAR(255),
    reason_for_regimen_change_first_line              VARCHAR(255),
    other_reason_for_regimen_change_first_line        VARCHAR(255),
    art_regimen_switch_to_second_line                 VARCHAR(255),
    art_second_line_switch_date                       DATE,
    substitution_regimen_for_adults_second_line       VARCHAR(255),
    children_second_line_regimens                     VARCHAR(255),
    reason_for_regimen_change_second_line             VARCHAR(255),
    other_reason_for_regimen_change_second_line       VARCHAR(255),
    art_treatment_interruption                        VARCHAR(255),
    interruption_type                                 VARCHAR(255),
    art_treatment_stop_or_lost_date                   DATE,
    art_treatment_reasons_for_stop                    VARCHAR(255),
    date_if_restarted                                 DATE
);
    
    SELECT "Successfully created flat_encounter_hiv_care_enrolment table";