CREATE TABLE IF NOT EXISTS ADDRESSES
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    city            VARCHAR                                 NOT NULL,
    street          VARCHAR                                 NOT NULL,
    house_number    INTEGER                                 NOT NULL,
    building_number INTEGER,
    letter          VARCHAR,
    CONSTRAINT pk_address PRIMARY KEY (id),
    CONSTRAINT UQ_ADDRESSES UNIQUE (city, street, house_number, building_number, letter)
);

CREATE TABLE IF NOT EXISTS REQUISITES
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    address_id BIGINT,
    index      INTEGER,
    phone      VARCHAR,
    fax        VARCHAR,
    email      VARCHAR,
    CONSTRAINT pk_requisites PRIMARY KEY (id),
    CONSTRAINT FK_DEPARTMENTS_ON_ADDRESSES FOREIGN KEY (address_id) REFERENCES addresses (id)
);

CREATE TABLE IF NOT EXISTS ORGANIZATIONS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    organization            VARCHAR                                 NOT NULL,
    short_name_organization VARCHAR                                 NOT NULL,
    requisites_id           BIGINT,
    CONSTRAINT pk_organization PRIMARY KEY (id),
    CONSTRAINT UQ_ORGANIZATIONS UNIQUE (organization),
    CONSTRAINT FK_ORGANIZATIONS_ON_REQUISITES FOREIGN KEY (requisites_id) REFERENCES requisites (id)
);

CREATE TABLE IF NOT EXISTS BRANCHES
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    branch            VARCHAR                                 NOT NULL,
    short_name_branch VARCHAR                                 NOT NULL,
    requisites_id     BIGINT,
    organization_id   BIGINT                                  NOT NULL,
    CONSTRAINT pk_branch PRIMARY KEY (id),
    CONSTRAINT UQ_BRANCHES UNIQUE (branch),
    CONSTRAINT FK_BRANCHES_ON_REQUISITES FOREIGN KEY (requisites_id) REFERENCES requisites (id),
    CONSTRAINT FK_BRANCHES_ON_ORGANIZATIONS FOREIGN KEY (organization_id) REFERENCES organizations (id)
);

CREATE TABLE IF NOT EXISTS DEPARTMENTS
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    department            VARCHAR                                 NOT NULL,
    short_name_department VARCHAR                                 NOT NULL,
    department_number     INTEGER,
    requisites_id         BIGINT,
    branch_id             BIGINT                                  NOT NULL,
    CONSTRAINT pk_department PRIMARY KEY (id),
    CONSTRAINT UQ_DEPARTMENTS UNIQUE (department, department_number),
    CONSTRAINT FK_DEPARTMENTS_ON_REQUISITES FOREIGN KEY (requisites_id) REFERENCES requisites (id)
);

CREATE TABLE IF NOT EXISTS LICENSES
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type     VARCHAR                                 NOT NULL,
    license_number    VARCHAR                                 NOT NULL,
    start_data        DATE                                    NOT NULL,
    end_data          DATE                                    NOT NULL,
    issued_license_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_licenses PRIMARY KEY (id),
    CONSTRAINT UQ_LICENSES UNIQUE (license_number, start_data, end_data)
);

CREATE TABLE IF NOT EXISTS ORGANIZATIONS_LICENSES
(
    organization_id   BIGINT,
    license_id BIGINT,
    CONSTRAINT pk_organizations_of_licenses PRIMARY KEY (organization_id, license_id)
);

CREATE TABLE IF NOT EXISTS BRANCHES_LICENSES
(
    branch_id   BIGINT,
    license_id BIGINT,
    CONSTRAINT pk_branches_of_licenses PRIMARY KEY (branch_id, license_id)
);

CREATE TABLE IF NOT EXISTS DEPARTMENTS_LICENSES
(
    department_id   BIGINT,
    license_id BIGINT,
    CONSTRAINT pk_departments_of_licenses PRIMARY KEY (department_id, license_id)
);

CREATE TABLE IF NOT EXISTS BUILDINGS
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    building_type VARCHAR                                 NOT NULL,
    login         VARCHAR,
    department_id BIGINT                                  NOT NULL,
    address_id    BIGINT                                  NOT NULL,
    CONSTRAINT pk_building PRIMARY KEY (id),
    CONSTRAINT UQ_BUILDINGS UNIQUE (building_type, login),
    CONSTRAINT FK_BUILDINGS_ON_ADDRESSES FOREIGN KEY (address_id) REFERENCES addresses (id),
    CONSTRAINT FK_BUILDINGS_ON_DEPARTMENTS FOREIGN KEY (department_id) REFERENCES departments (id)
);

CREATE TABLE IF NOT EXISTS OBJECT_TYPES
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    object_name VARCHAR                                 NOT NULL,
    orientation VARCHAR,
    volume      INTEGER,
    model       VARCHAR,
    CONSTRAINT pk_objectsType PRIMARY KEY (id),
    CONSTRAINT UQ_OBJECT_TYPES UNIQUE (object_name, volume, orientation, model)
);

CREATE TABLE IF NOT EXISTS DOCUMENTATIONS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    view                    VARCHAR,
    number                  VARCHAR,
    title                   VARCHAR                                 NOT NULL,
    methodological_document BOOLEAN                                 NOT NULL,
    regulatory_document     BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_documentation PRIMARY KEY (id),
    CONSTRAINT UQ_DOCUMENTATIONS UNIQUE (title)
);

CREATE TABLE IF NOT EXISTS OBJECT_TYPES_DOCUMENTATIONS
(
    object_type_id BIGINT,
    document_id    BIGINT,
    CONSTRAINT pk_object_types_of_documentations PRIMARY KEY (object_type_id, document_id)
);

CREATE TABLE IF NOT EXISTS REPAIR_METHODS
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    method_name VARCHAR                                 NOT NULL,
    CONSTRAINT pk_repairMethod PRIMARY KEY (id),
    CONSTRAINT UQ_REPAIR_METHODS UNIQUE (method_name)
);

CREATE TABLE IF NOT EXISTS OBJECT_TYPES_REPAIR_METHODS
(
    object_type_id   BIGINT,
    repair_method_id BIGINT,
    CONSTRAINT pk_object_types_of_repair_methods PRIMARY KEY (object_type_id, repair_method_id)
);

CREATE TABLE IF NOT EXISTS OBJECT_PASSPORT_DATA_TEMPLATES
(
    id                           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    sequential_subsection_number DOUBLE PRECISION                        NOT NULL,
    data_name                    VARCHAR                                 NOT NULL,
    apply_protocol               BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_objectPassportDataTemplate PRIMARY KEY (id),
    CONSTRAINT UQ_OBJECT_PASSPORT_DATA_TEMPLATES UNIQUE (sequential_subsection_number, data_name)
);

CREATE TABLE IF NOT EXISTS OBJECT_TYPES_OBJECT_PASSPORT_DATA_TEMPLATES
(
    object_type_id            BIGINT,
    passport_data_template_id BIGINT,
    CONSTRAINT pk_object_types_of_object_passport_data_templates PRIMARY KEY (object_type_id, passport_data_template_id)
);

CREATE TABLE IF NOT EXISTS DEFECTS
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    defect_name VARCHAR                                 NOT NULL,
    CONSTRAINT pk_defect PRIMARY KEY (id),
    CONSTRAINT UQ_DEFECTS UNIQUE (defect_name)
);

CREATE TABLE IF NOT EXISTS SIZE_PARAMETERS
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    parameters_name  VARCHAR                                 NOT NULL,
    unit_measurement VARCHAR                                 NOT NULL,
    CONSTRAINT pk_sizeParameters PRIMARY KEY (id),
    CONSTRAINT UQ_SIZE_PARAMETERS UNIQUE (parameters_name)
);

CREATE TABLE IF NOT EXISTS DEFECTS_SIZE_PARAMETERS
(
    defect_id         BIGINT,
    size_parameter_id BIGINT,
    CONSTRAINT pk_defects_of_defect_parameters PRIMARY KEY (defect_id, size_parameter_id)
);

CREATE TABLE IF NOT EXISTS OBJECT_TYPES_DEFECTS
(
    object_type_id BIGINT,
    defect_id      BIGINT,
    CONSTRAINT pk_object_types_of_defects PRIMARY KEY (object_type_id, defect_id)
);

CREATE TABLE IF NOT EXISTS ELEMENTS
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    objects_type_id BIGINT                                  NOT NULL,
    element_name    VARCHAR                                 NOT NULL,
    CONSTRAINT pk_element PRIMARY KEY (id),
    CONSTRAINT UQ_ELEMENTS UNIQUE (objects_type_id, element_name)
);

CREATE TABLE IF NOT EXISTS SUB_ELEMENTS
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    sub_element_name VARCHAR                                 NOT NULL,
    CONSTRAINT pk_subElement PRIMARY KEY (id),
    CONSTRAINT UQ_SUB_ELEMENTS UNIQUE (sub_element_name)
);

CREATE TABLE IF NOT EXISTS ELEMENTS_SUB_ELEMENTS
(
    element_id     BIGINT,
    sub_element_id BIGINT,
    CONSTRAINT pk_elements_of_subElements PRIMARY KEY (element_id, sub_element_id)
);

CREATE TABLE IF NOT EXISTS OBJECT_TYPES_ELEMENTS
(
    object_type_id BIGINT,
    element_id     BIGINT,
    CONSTRAINT pk_object_types_of_elements PRIMARY KEY (object_type_id, element_id)
);

CREATE TABLE IF NOT EXISTS NORMS
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    element_id        BIGINT                                  NOT NULL,
    sub_element_id    BIGINT,
    diameter          INTEGER,
    thickness         FLOAT,
    min_in_percent    FLOAT,
    min               FLOAT                                   NOT NULL,
    measurement_error FLOAT                                   NOT NULL,
    CONSTRAINT pk_norm PRIMARY KEY (id),
    CONSTRAINT FK_NORMS_ON_ELEMENTS FOREIGN KEY (element_id) REFERENCES elements (id),
    CONSTRAINT FK_NORMS_ON_SUB_ELEMENTS FOREIGN KEY (sub_element_id) REFERENCES sub_elements (id)
);

CREATE TABLE IF NOT EXISTS OBJECT_TYPES_NORMS
(
    object_type_id BIGINT,
    norm_id        BIGINT,
    CONSTRAINT pk_object_types_of_norms PRIMARY KEY (object_type_id, norm_id)
);

CREATE TABLE IF NOT EXISTS SURVEY_OBJECTS
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    objects_type_id   BIGINT                                  NOT NULL,
    stationary_number INTEGER,
    building_id       BIGINT                                  NOT NULL,
    CONSTRAINT pk_surveyObject PRIMARY KEY (id),
    CONSTRAINT UQ_SURVEY_OBJECTS UNIQUE (objects_type_id, building_id),
    CONSTRAINT FK_SURVEY_OBJECTS_ON_OBJECT_TYPES FOREIGN KEY (objects_type_id) REFERENCES object_types (id),
    CONSTRAINT FK_SURVEY_OBJECTS_ON_BUILDINGS FOREIGN KEY (building_id) REFERENCES buildings (id)
);

CREATE TABLE IF NOT EXISTS SURVEY_OBJECTS_ELEMENTS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    element_id              BIGINT                                  NOT NULL,
    sub_element_id          BIGINT,
    thickness               FLOAT,
    pipe_diameter_min       INTEGER,
    pipe_wall_thickness_min FLOAT,
    pipe_diameter_max       INTEGER,
    pipe_wall_thickness_max FLOAT,
    survey_object_id        BIGINT                                  NOT NULL,
    CONSTRAINT pk_surveyObjectsElement PRIMARY KEY (id),
    CONSTRAINT FK_SURVEY_OBJECTS_ELEMENTS_ON_ELEMENTS FOREIGN KEY (element_id) REFERENCES sub_elements (id),
    CONSTRAINT FK_SURVEY_OBJECTS_ELEMENTS_ON_SUB_ELEMENTS FOREIGN KEY (sub_element_id) REFERENCES sub_elements (id),
    CONSTRAINT FK_SURVEY_OBJECTS_ELEMENTS_ON_SURVEY_OBJECTS FOREIGN KEY (survey_object_id) REFERENCES survey_objects (id)
);

CREATE TABLE IF NOT EXISTS EMPLOYEES
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name            VARCHAR                                 NOT NULL,
    patronymic      VARCHAR                                 NOT NULL,
    surname         VARCHAR                                 NOT NULL,
    post            VARCHAR                                 NOT NULL,
    requisites_id   BIGINT,
    organization_id BIGINT                                  NOT NULL,
    branch_id       BIGINT                                  NOT NULL,
    department_id   BIGINT                                  NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id),
    CONSTRAINT UQ_EMPLOYEES UNIQUE (name, patronymic, surname),
    CONSTRAINT FK_EMPLOYEES_ON_REQUISITES FOREIGN KEY (requisites_id) REFERENCES requisites (id),
    CONSTRAINT FK_EMPLOYEES_ON_ORGANIZATIONS FOREIGN KEY (organization_id) REFERENCES organizations (id),
    CONSTRAINT FK_EMPLOYEES_ON_BRANCHES FOREIGN KEY (branch_id) REFERENCES branches (id),
    CONSTRAINT FK_EMPLOYEES_ON_DEPARTMENTS FOREIGN KEY (department_id) REFERENCES departments (id)
);

CREATE TABLE IF NOT EXISTS CERTIFICATES
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type      VARCHAR                                 NOT NULL,
    certificate_number VARCHAR                                 NOT NULL,
    control_type       VARCHAR                                 NOT NULL,
    level              VARCHAR                                 NOT NULL,
    start_date         DATE                                    NOT NULL,
    end_date           DATE                                    NOT NULL,
    points             VARCHAR,
    organization_id    BIGINT                                  NOT NULL,
    employee_id        BIGINT                                  NOT NULL,
    CONSTRAINT pk_certificate PRIMARY KEY (id),
    CONSTRAINT UQ_CERTIFICATES UNIQUE (control_type, employee_id),
    CONSTRAINT FK_CERTIFICATE_ON_ORGANIZATION FOREIGN KEY (organization_id) REFERENCES organizations (id),
    CONSTRAINT FK_CERTIFICATE_ON_EMPLOYEES FOREIGN KEY (employee_id) REFERENCES employees (id)
);

CREATE TABLE IF NOT EXISTS MEASURING_TOOLS
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    toll                   VARCHAR                                 NOT NULL,
    model                  VARCHAR                                 NOT NULL,
    work_number            VARCHAR,
    purpose                VARCHAR,
    manufacturing          DATE,
    exploitation           DATE,
    manufacturer_id        BIGINT,
    measuring_range        VARCHAR,
    characteristics        VARCHAR,
    tool_owner_id          BIGINT                                  NOT NULL,
    verification_date      DATE,
    next_verification_date DATE,
    organization_id        BIGINT,
    certificate_number     VARCHAR,
    registry               VARCHAR,
    note                   VARCHAR,
    control_type           VARCHAR                                 NOT NULL,
    employee_id            BIGINT,
    CONSTRAINT pk_measuringTool PRIMARY KEY (id),
    CONSTRAINT UQ_MEASURING_TOOL UNIQUE (toll, model, work_number),
    CONSTRAINT FK_MEASURING_TOOL_ON_ORGANIZATION
        FOREIGN KEY (manufacturer_id) REFERENCES organizations (id),
    FOREIGN KEY (tool_owner_id) REFERENCES organizations (id),
    FOREIGN KEY (organization_id) REFERENCES organizations (id),
    CONSTRAINT FK_MEASURING_TOOL_ON_EMPLOYEES FOREIGN KEY (employee_id) REFERENCES employees (id)
);

CREATE TABLE IF NOT EXISTS REPORTING_DOCUMENTS
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document       VARCHAR                                 NOT NULL,
    document_title VARCHAR                                 NOT NULL,
    document_type  VARCHAR                                 NOT NULL,
    protocol_type  VARCHAR,
    CONSTRAINT pk_reportingDocument PRIMARY KEY (id),
    CONSTRAINT UQ_REPORTING_DOCUMENTS UNIQUE (document, document_title)
);

CREATE TABLE IF NOT EXISTS APPLICATIONS
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    organization_id       BIGINT,
    branch_id             BIGINT,
    department_id         BIGINT,
    date                  DATE,
    address_id            BIGINT                                  NOT NULL,
    survey_object_id      BIGINT                                  NOT NULL,
    work_performed        VARCHAR                                 NOT NULL,
    reporting_document_id BIGINT                                  NOT NULL,
    application_status    VARCHAR                                 NOT NULL,
    task_source           VARCHAR                                 NOT NULL,
    comment               VARCHAR,
    need_drawing          BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_application PRIMARY KEY (id),
    CONSTRAINT UQ_APPLICATIONS UNIQUE (date, address_id, survey_object_id),
    CONSTRAINT FK_APPLICATIONS_ON_ADDRESSES FOREIGN KEY (address_id) REFERENCES addresses (id),
    CONSTRAINT FK_APPLICATIONS_ON_SURVEY_OBJECTS FOREIGN KEY (survey_object_id) REFERENCES survey_objects (id),
    CONSTRAINT FK_APPLICATIONS_ON_ORGANIZATIONS FOREIGN KEY (organization_id) REFERENCES organizations (id),
    CONSTRAINT FK_APPLICATIONS_ON_BRANCHES FOREIGN KEY (branch_id) REFERENCES branches (id),
    CONSTRAINT FK_APPLICATIONS_ON_DEPARTMENTS FOREIGN KEY (department_id) REFERENCES departments (id),
    CONSTRAINT FK_APPLICATIONS_ON_REPORTING_DOCUMENTS FOREIGN KEY (reporting_document_id)
        REFERENCES reporting_documents (id)
);

CREATE TABLE IF NOT EXISTS REPORTING_DOCUMENT_DATA
(
    id                   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    application_id       BIGINT                                  NOT NULL,
    document_number      INTEGER                                 NOT NULL,
    employee_document_id BIGINT,
    employee_drawing_id  BIGINT,
    document_start_date  TIMESTAMP,
    document_end_date    TIMESTAMP,
    transfer_date        DATE,
    next_survey_date     DATE,
    document_status      VARCHAR                                 NOT NULL,
    drawing_status       VARCHAR                                 NOT NULL,
    document_path        VARCHAR,
    drawing_path         VARCHAR,
    CONSTRAINT pk_reportingDocumentData PRIMARY KEY (id),
    CONSTRAINT UQ_REPORTING_DOCUMENT_DATA UNIQUE (application_id),
    CONSTRAINT FK_REPORTING_DOCUMENT_DATA_ON_EMPLOYEES
        FOREIGN KEY (employee_document_id) REFERENCES employees (id),
    FOREIGN KEY (employee_drawing_id) REFERENCES employees (id),
    CONSTRAINT FK_REPORTING_DOCUMENT_DATA_ON_APPLICATIONS FOREIGN KEY (application_id) REFERENCES applications (id)
);

CREATE TABLE IF NOT EXISTS DOCUMENT_REMARKS
(
    id                         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_remark_text       VARCHAR,
    drawing_remark_text        VARCHAR,
    employee_document_id       BIGINT,
    employee_drawing_id        BIGINT,
    reporting_document_data_id BIGINT                                  NOT NULL,
    fixed                      BOOLEAN,
    CONSTRAINT pk_documentRemark PRIMARY KEY (id),
    CONSTRAINT FK_DOCUMENT_REMARKS_ON_EMPLOYEES
        FOREIGN KEY (employee_document_id) REFERENCES employees (id),
    FOREIGN KEY (employee_drawing_id) REFERENCES employees (id),
    CONSTRAINT FK_DOCUMENT_REMARKS_ON_REPORTING_DOCUMENT_DATA FOREIGN KEY (reporting_document_data_id)
        REFERENCES reporting_document_data (id)
);

CREATE TABLE IF NOT EXISTS OBJECT_PASSPORTS
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_object_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_objectPassport PRIMARY KEY (id),
    CONSTRAINT FK_PASSPORTS_ON_OBJECTS FOREIGN KEY (survey_object_id) REFERENCES survey_objects (id)
);

CREATE TABLE IF NOT EXISTS SURVEY_OBJECT_SURVEYS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    date               VARCHAR                                 NOT NULL,
    survey_description VARCHAR                                 NOT NULL,
    survey_number      VARCHAR                                 NOT NULL,
    organization       VARCHAR                                 NOT NULL,
    passport_id        BIGINT                                  NOT NULL,
    date_next_survey   VARCHAR,
    CONSTRAINT pk_surveyObjectSurveys PRIMARY KEY (id),
    CONSTRAINT UQ_OBJECT_SURVEYS UNIQUE (survey_description, survey_number, organization),
    CONSTRAINT FK_OBJECT_SURVEYS_ON_OBJECT_PASSPORTS FOREIGN KEY (passport_id) REFERENCES object_passports (id)
);

CREATE TABLE IF NOT EXISTS SURVEY_OBJECT_REPAIRS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    date               VARCHAR                                 NOT NULL,
    repair_description VARCHAR                                 NOT NULL,
    organization       VARCHAR                                 NOT NULL,
    passport_id        BIGINT                                  NOT NULL,
    CONSTRAINT pk_surveyObjectRepairRepair PRIMARY KEY (id),
    CONSTRAINT UQ_SURVEY_OBJECT_REPAIRS UNIQUE (date, repair_description, organization),
    CONSTRAINT FK_SURVEY_OBJECT_REPAIRS_ON_OBJECT_PASSPORTS FOREIGN KEY (passport_id) REFERENCES object_passports (id)
);

CREATE TABLE IF NOT EXISTS DATA_PASSPORT_OF_OBJECTS
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    template_id BIGINT                                  NOT NULL,
    meaning     VARCHAR                                 NOT NULL,
    passport_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_dataPassportOfObject PRIMARY KEY (id),
    CONSTRAINT UQ_DATA_PASSPORT_OF_OBJECTS UNIQUE (template_id, meaning, passport_id),
    CONSTRAINT FK_DATA_PASSPORT_OF_OBJECTS_ON_OBJECT_PASSPORT_DATA_TEMPLATES
        FOREIGN KEY (template_id) REFERENCES object_passport_data_templates (id),
    CONSTRAINT FK_DATA_PASSPORT_OF_OBJECTS_ON_OBJECT_PASSPORTS FOREIGN KEY (passport_id) REFERENCES object_passports (id)
);

CREATE TABLE IF NOT EXISTS APPLICATIONS_EMPLOYEES
(
    application_id BIGINT,
    employee_id    BIGINT,
    CONSTRAINT pk_application_of_employees PRIMARY KEY (application_id, employee_id)
);