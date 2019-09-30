/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.form.util;



/**
 *
 * @author User
 */
@javax.persistence.Entity(name = "Customer")
@javax.persistence.Table(catalog = "theburma_automation", name = "customer")
public class Customer implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "CUSTOMER_ID_GENERATOR")
	@javax.persistence.Id
	@javax.persistence.SequenceGenerator(catalog = "theburma_automation", sequenceName = "CUSTOMER_ID_SEQ", name = "CUSTOMER_ID_GENERATOR")
	@javax.persistence.Column(unique = true, nullable = false, name = "id", table = "customer")
	private java.lang.Long id;

	@javax.persistence.Column(name = "customerId", table = "customer")
	private java.lang.String customerId;

	@javax.persistence.Column(name = "firstname", table = "customer")
	private java.lang.String firstname;

	@javax.persistence.Column(name = "middlename", table = "customer")
	private java.lang.String middlename;

	@javax.persistence.Column(name = "lastname", table = "customer")
	private java.lang.String lastname;

	@javax.persistence.Column(name = "nationality", table = "customer")
	private java.lang.String nationality;

	@javax.persistence.Column(name = "dob", table = "customer")
	private java.util.Date dob;

	@javax.persistence.Column(name = "gender", table = "customer")
	private java.lang.String gender;

	@javax.persistence.Column(name = "maritalstatus", table = "customer")
	private java.lang.String maritalstatus;

	@javax.persistence.Column(name = "nrc", table = "customer")
	private java.lang.String nrc;

	@javax.persistence.Column(name = "passport", table = "customer")
	private java.lang.String passport;

	@javax.persistence.Column(name = "drivinglicense", table = "customer")
	private java.lang.String drivinglicense;

	@javax.persistence.Column(name = "address", table = "customer")
	private java.lang.String address;

	@javax.persistence.Column(name = "address2", table = "customer")
	private java.lang.String address2;

	@javax.persistence.Column(name = "city", table = "customer")
	private java.lang.String city;

	@javax.persistence.Column(name = "state", table = "customer")
	private String state;

	@javax.persistence.Column(name = "country", table = "customer")
	private String country;

	@javax.persistence.Column(name = "postalcode", table = "customer")
	private java.lang.String postalcode;

	@javax.persistence.Column(name = "homephone", table = "customer")
	private java.lang.String homephone;

	@javax.persistence.Column(name = "mobilephone", table = "customer")
	private java.lang.String mobilephone;

	@javax.persistence.Column(name = "workphone", table = "customer")
	private java.lang.String workphone;

	@javax.persistence.Column(name = "workemail", table = "customer")
	private java.lang.String workemail;

	@javax.persistence.Column(name = "privateemail", table = "customer")
	private java.lang.String privateemail;

	@javax.persistence.Column(name = "professions", table = "customer")
	private java.lang.String professions;

	@javax.persistence.Column(name = "ethnicity", table = "customer")
	private java.lang.String ethnicity;

	@javax.persistence.Column(name = "created", table = "customer")
	private java.util.Date created;

	@javax.persistence.Column(name = "updated", table = "customer")
	private java.util.Date updated;

	@javax.persistence.Column(name = "status", table = "customer")
	private int status;

	@javax.persistence.Column(name = "department", table = "customer")
	private java.lang.Long department;

	@javax.persistence.Column(name = "title", table = "customer")
	private java.lang.String title;

	@javax.persistence.Column(name = "otherId", table = "customer")
	private java.lang.String otherId;

	@javax.persistence.Column(name = "driving_license_exp_date", table = "customer")
	private java.util.Date driving_license_exp_date;

	@javax.persistence.Column(name = "occupation", table = "customer")
	private java.lang.String occupation;

	@javax.persistence.Column(name = "workaddress", table = "customer")
	private java.lang.String workaddress;

	@javax.persistence.Column(name = "immigration_status", table = "customer")
	private java.lang.String immigration_status;

	@javax.persistence.Column(name = "notes", table = "customer")
	private java.lang.String notes;

	@javax.persistence.Column(name = "ward", table = "customer")
	private java.lang.String ward;

	@javax.persistence.Column(name = "township", table = "customer")
	private java.lang.String township;

	@javax.persistence.Column(name = "village", table = "customer")
	private java.lang.String village;

	@javax.persistence.Column(name = "country_of_birth", table = "customer")
	private java.util.Date country_of_birth;

	@javax.persistence.Column(name = "state_province_of_birth", table = "customer")
	private java.lang.String state_province_of_birth;

	@javax.persistence.Column(name = "race", table = "customer")
	private java.lang.String race;

	@javax.persistence.Column(name = "other_race", table = "customer")
	private java.lang.String other_race;

	@javax.persistence.Column(name = "type_of_travel_document", table = "customer")
	private java.lang.String type_of_travel_document;

	@javax.persistence.Column(name = "travel_document_number", table = "customer")
	private java.lang.String travel_document_number;

	@javax.persistence.Column(name = "travel_document_issued_date", table = "customer")
	private java.util.Date travel_document_issued_date;

	@javax.persistence.Column(name = "travel_document_expiry_date", table = "customer")
	private java.util.Date travel_document_expiry_date;

	@javax.persistence.Column(name = "country_of_issue", table = "customer")
	private java.lang.String country_of_issue;

	@javax.persistence.Column(name = "place_of_issue", table = "customer")
	private java.lang.String place_of_issue;

	@javax.persistence.Column(name = "country_of_origin", table = "customer")
	private java.lang.String country_of_origin;

	@javax.persistence.Column(name = "prefecture_of_origin", table = "customer")
	private java.lang.String prefecture_of_origin;

	@javax.persistence.Column(name = "highest_academic", table = "customer")
	private java.lang.String highest_academic;

	@javax.persistence.Column(name = "religion_denomination", table = "customer")
	private java.lang.String religion_denomination;

	@javax.persistence.Column(name = "purpose_of_visit", table = "customer")
	private java.lang.String purpose_of_visit;

	@javax.persistence.Column(name = "intended_date_of_arrival", table = "customer")
	private java.util.Date intended_date_of_arrival;

	@javax.persistence.Column(name = "intended_period_of_stay", table = "customer")
	private int intended_period_of_stay;

	@javax.persistence.Column(name = "nationality_of_spouse", table = "customer")
	private java.lang.String nationality_of_spouse;

	@javax.persistence.Column(name = "next_of_kin", table = "customer")
	private java.lang.String next_of_kin;

	@javax.persistence.Column(name = "relative", table = "customer")
	private java.lang.String relative;

	@javax.persistence.Column(name = "friend", table = "customer")
	private java.lang.String friend;

	@javax.persistence.Column(name = "hotel", table = "customer")
	private java.lang.String hotel;

	@javax.persistence.Column(name = "others", table = "customer")
	private java.lang.String others;

	@javax.persistence.Column(name = "building_name", table = "customer")
	private java.lang.String building_name;

	@javax.persistence.Column(name = "building_or_unit_number", table = "customer")
	private java.lang.String building_or_unit_number;

	@javax.persistence.Column(name = "floor_number", table = "customer")
	private java.lang.String floor_number;

	@javax.persistence.Column(name = "road_street", table = "customer")
	private java.lang.String road_street;

	@javax.persistence.Column(name = "reference_name", table = "customer")
	private java.lang.String reference_name;

	@javax.persistence.Column(name = "reference_contact_number", table = "customer")
	private java.lang.String reference_contact_number;

	@javax.persistence.Column(name = "nickname", table = "customer")
	private java.lang.String nickname;

	@javax.persistence.Column(name = "childhood_name", table = "customer")
	private java.lang.String childhood_name;

	@javax.persistence.Column(name = "artist_name", table = "customer")
	private java.lang.String artist_name;

	@javax.persistence.Column(name = "other_name", table = "customer")
	private java.lang.String other_name;

	@javax.persistence.Column(name = "photo", table = "customer")
	private java.lang.String photo;

	@javax.persistence.Column(name = "blood_type", table = "customer")
	private java.lang.String blood_type;

	@javax.persistence.Column(name = "eye_colour", table = "customer")
	private java.lang.String eye_colour;

	@javax.persistence.Column(name = "height", table = "customer")
	private java.lang.String height;

	@javax.persistence.Column(name = "distinct_feature", table = "customer")
	private java.lang.String distinct_feature;

	@javax.persistence.Column(name = "hair_colour", table = "customer")
	private java.lang.String hair_colour;

	@javax.persistence.Column(name = "body_weight", table = "customer")
	private java.lang.String body_weight;

	@javax.persistence.Column(name = "born_in", table = "customer")
	private java.lang.String born_in;

	@javax.persistence.Column(name = "breath_in", table = "customer")
	private java.lang.String breath_in;

	@javax.persistence.Column(name = "parents", table = "customer")
	private java.lang.String parents;

	@javax.persistence.Column(name = "children", table = "customer")
	private java.lang.String children;

	@javax.persistence.Column(name = "nrc_copy_front", table = "customer")
	private java.lang.String nrc_copy_front;

	@javax.persistence.Column(name = "nrc_copy_back", table = "customer")
	private java.lang.String nrc_copy_back;

	@javax.persistence.Column(name = "passport_copy", table = "customer")
	private java.lang.String passport_copy;

	@javax.persistence.Column(name = "driving_license_copy", table = "customer")
	private java.lang.String driving_license_copy;

	@javax.persistence.Column(name = "house_hold_list_copy_front", table = "customer")
	private java.lang.String house_hold_list_copy_front;

	@javax.persistence.Column(name = "house_hold_list_copy_back", table = "customer")
	private java.lang.String house_hold_list_copy_back;

	@javax.persistence.Column(name = "police_recommendation_letter_copy", table = "customer")
	private java.lang.String police_recommendation_letter_copy;

	@javax.persistence.Column(name = "administrative_recommendation_letter_copy", table = "customer")
	private java.lang.String administrative_recommendation_letter_copy;

	@javax.persistence.Column(name = "labour_card_copy", table = "customer")
	private java.lang.String labour_card_copy;

	@javax.persistence.Column(name = "staff_card_copy", table = "customer")
	private java.lang.String staff_card_copy;

	@javax.persistence.Column(name = "family_lawyer", table = "customer")
	private java.lang.String family_lawyer;

	@javax.persistence.Column(name = "family_doctor", table = "customer")
	private java.lang.String family_doctor;

	@javax.persistence.Column(name = "custom1", table = "customer")
	private java.lang.String custom1;

	@javax.persistence.Column(name = "custom2", table = "customer")
	private java.lang.String custom2;

	@javax.persistence.Column(name = "custom3", table = "customer")
	private java.lang.String custom3;

	@javax.persistence.Column(name = "custom4", table = "customer")
	private java.lang.String custom4;

	@javax.persistence.Column(name = "custom5", table = "customer")
	private java.lang.String custom5;

	@javax.persistence.Column(name = "custom6", table = "customer")
	private java.lang.String custom6;

	@javax.persistence.Column(name = "custom7", table = "customer")
	private java.lang.String custom7;

	@javax.persistence.Column(name = "custom8", table = "customer")
	private java.lang.String custom8;

	@javax.persistence.Column(name = "custom9", table = "customer")
	private java.lang.String custom9;

	@javax.persistence.Column(name = "custom10", table = "customer")
	private java.lang.String custom10;

	@javax.persistence.Column(name = "attachment1", table = "customer")
	private java.lang.String attachment1;

	@javax.persistence.Column(name = "attachment2", table = "customer")
	private java.lang.String attachment2;

	@javax.persistence.Column(name = "attachment3", table = "customer")
	private java.lang.String attachment3;

	@javax.persistence.Column(name = "attachment4", table = "customer")
	private java.lang.String attachment4;

	@javax.persistence.Column(name = "attachment5", table = "customer")
	private java.lang.String attachment5;

	@javax.persistence.Column(name = "attachment6", table = "customer")
	private java.lang.String attachment6;

	@javax.persistence.Column(name = "attachment7", table = "customer")
	private java.lang.String attachment7;

	@javax.persistence.Column(name = "attachment8", table = "customer")
	private java.lang.String attachment8;

	@javax.persistence.Column(name = "attachment9", table = "customer")
	private java.lang.String attachment9;

	@javax.persistence.Column(name = "attachment10", table = "customer")
	private java.lang.String attachment10;

	@javax.persistence.Column(name = "custom_date1", table = "customer")
	private java.util.Date custom_date1;

	@javax.persistence.Column(name = "custom_date2", table = "customer")
	private java.util.Date custom_date2;

	@javax.persistence.Column(name = "custom_date3", table = "customer")
	private java.util.Date custom_date3;

	@javax.persistence.Column(name = "custom_date4", table = "customer")
	private java.util.Date custom_date4;

	@javax.persistence.Column(name = "custom_date5", table = "customer")
	private java.util.Date custom_date5;

	public Customer() {
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(java.lang.String customerId) {
		this.customerId = customerId;
	}

	public java.lang.String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(java.lang.String firstname) {
		this.firstname = firstname;
	}

	public java.lang.String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(java.lang.String middlename) {
		this.middlename = middlename;
	}

	public java.lang.String getLastname() {
		return this.lastname;
	}

	public void setLastname(java.lang.String lastname) {
		this.lastname = lastname;
	}

	public java.lang.String getNationality() {
		return this.nationality;
	}

	public void setNationality(java.lang.String nationality) {
		this.nationality = nationality;
	}

	public java.util.Date getDob() {
		return this.dob;
	}

	public void setDob(java.util.Date dob) {
		this.dob = dob;
	}

	public java.lang.String getGender() {
		return this.gender;
	}

	public void setGender(java.lang.String gender) {
		this.gender = gender;
	}

	public java.lang.String getMaritalstatus() {
		return this.maritalstatus;
	}

	public void setMaritalstatus(java.lang.String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public java.lang.String getNrc() {
		return this.nrc;
	}

	public void setNrc(java.lang.String nrc) {
		this.nrc = nrc;
	}

	public java.lang.String getPassport() {
		return this.passport;
	}

	public void setPassport(java.lang.String passport) {
		this.passport = passport;
	}

	public java.lang.String getDrivinglicense() {
		return this.drivinglicense;
	}

	public void setDrivinglicense(java.lang.String drivinglicense) {
		this.drivinglicense = drivinglicense;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getAddress2() {
		return this.address2;
	}

	public void setAddress2(java.lang.String address2) {
		this.address2 = address2;
	}

	public java.lang.String getCity() {
		return this.city;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	public java.lang.String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(java.lang.String postalcode) {
		this.postalcode = postalcode;
	}

	public java.lang.String getHomephone() {
		return this.homephone;
	}

	public void setHomephone(java.lang.String homephone) {
		this.homephone = homephone;
	}

	public java.lang.String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(java.lang.String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public java.lang.String getWorkphone() {
		return this.workphone;
	}

	public void setWorkphone(java.lang.String workphone) {
		this.workphone = workphone;
	}

	public java.lang.String getWorkemail() {
		return this.workemail;
	}

	public void setWorkemail(java.lang.String workemail) {
		this.workemail = workemail;
	}

	public java.lang.String getPrivateemail() {
		return this.privateemail;
	}

	public void setPrivateemail(java.lang.String privateemail) {
		this.privateemail = privateemail;
	}

	public java.lang.String getProfessions() {
		return this.professions;
	}

	public void setProfessions(java.lang.String professions) {
		this.professions = professions;
	}

	public java.lang.String getEthnicity() {
		return this.ethnicity;
	}

	public void setEthnicity(java.lang.String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public java.util.Date getCreated() {
		return this.created;
	}

	public void setCreated(java.util.Date created) {
		this.created = created;
	}

	public java.util.Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(java.util.Date updated) {
		this.updated = updated;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Customer(java.lang.String customerId, java.lang.String firstname,
			java.lang.String middlename, java.lang.String lastname,
			java.lang.String nationality, java.util.Date dob,
			java.lang.String gender, java.lang.String maritalstatus,
			java.lang.String nrc, java.lang.String passport,
			java.lang.String drivinglicense, java.lang.String address,
			java.lang.String address2, java.lang.String city,
			java.lang.String state, java.lang.String country,
			java.lang.String postalcode, java.lang.String homephone,
			java.lang.String mobilephone, java.lang.String workphone,
			java.lang.String workemail, java.lang.String privateemail,
			java.lang.String professions, java.lang.String ethnicity,
			java.util.Date created, java.util.Date updated, int status) {
		this.customerId = customerId;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.nationality = nationality;
		this.dob = dob;
		this.gender = gender;
		this.maritalstatus = maritalstatus;
		this.nrc = nrc;
		this.passport = passport;
		this.drivinglicense = drivinglicense;
		this.address = address;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalcode = postalcode;
		this.homephone = homephone;
		this.mobilephone = mobilephone;
		this.workphone = workphone;
		this.workemail = workemail;
		this.privateemail = privateemail;
		this.professions = professions;
		this.ethnicity = ethnicity;
		this.created = created;
		this.updated = updated;
		this.status = status;
	}

	public java.lang.Long getDepartment() {
		return this.department;
	}

	public void setDepartment(java.lang.Long department) {
		this.department = department;
	}

	public Customer(java.lang.String customerId, java.lang.String firstname,
			java.lang.String middlename, java.lang.String lastname,
			java.lang.String nationality, java.util.Date dob,
			java.lang.String gender, java.lang.String maritalstatus,
			java.lang.String nrc, java.lang.String passport,
			java.lang.String drivinglicense, java.lang.String address,
			java.lang.String address2, java.lang.String city,
			java.lang.String state, java.lang.String country,
			java.lang.String postalcode, java.lang.String homephone,
			java.lang.String mobilephone, java.lang.String workphone,
			java.lang.String workemail, java.lang.String privateemail,
			java.lang.String professions, java.lang.String ethnicity,
			java.util.Date created, java.util.Date updated, int status,
			java.lang.Long department) {
		this.customerId = customerId;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.nationality = nationality;
		this.dob = dob;
		this.gender = gender;
		this.maritalstatus = maritalstatus;
		this.nrc = nrc;
		this.passport = passport;
		this.drivinglicense = drivinglicense;
		this.address = address;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalcode = postalcode;
		this.homephone = homephone;
		this.mobilephone = mobilephone;
		this.workphone = workphone;
		this.workemail = workemail;
		this.privateemail = privateemail;
		this.professions = professions;
		this.ethnicity = ethnicity;
		this.created = created;
		this.updated = updated;
		this.status = status;
		this.department = department;
	}

	public java.lang.String getTitle() {
		return this.title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getOtherId() {
		return this.otherId;
	}

	public void setOtherId(java.lang.String otherId) {
		this.otherId = otherId;
	}

	public java.util.Date getDriving_license_exp_date() {
		return this.driving_license_exp_date;
	}

	public void setDriving_license_exp_date(
			java.util.Date driving_license_exp_date) {
		this.driving_license_exp_date = driving_license_exp_date;
	}

	public java.lang.String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(java.lang.String occupation) {
		this.occupation = occupation;
	}

	public java.lang.String getWorkaddress() {
		return this.workaddress;
	}

	public void setWorkaddress(java.lang.String workaddress) {
		this.workaddress = workaddress;
	}

	public java.lang.String getImmigration_status() {
		return this.immigration_status;
	}

	public void setImmigration_status(java.lang.String immigration_status) {
		this.immigration_status = immigration_status;
	}

	public java.lang.String getNotes() {
		return this.notes;
	}

	public void setNotes(java.lang.String notes) {
		this.notes = notes;
	}

	public java.lang.String getWard() {
		return this.ward;
	}

	public void setWard(java.lang.String ward) {
		this.ward = ward;
	}

	public java.lang.String getTownship() {
		return this.township;
	}

	public void setTownship(java.lang.String township) {
		this.township = township;
	}

	public java.lang.String getVillage() {
		return this.village;
	}

	public void setVillage(java.lang.String village) {
		this.village = village;
	}

	public java.util.Date getCountry_of_birth() {
		return this.country_of_birth;
	}

	public void setCountry_of_birth(java.util.Date country_of_birth) {
		this.country_of_birth = country_of_birth;
	}

	public java.lang.String getState_province_of_birth() {
		return this.state_province_of_birth;
	}

	public void setState_province_of_birth(
			java.lang.String state_province_of_birth) {
		this.state_province_of_birth = state_province_of_birth;
	}

	public java.lang.String getRace() {
		return this.race;
	}

	public void setRace(java.lang.String race) {
		this.race = race;
	}

	public java.lang.String getOther_race() {
		return this.other_race;
	}

	public void setOther_race(java.lang.String other_race) {
		this.other_race = other_race;
	}

	public java.lang.String getType_of_travel_document() {
		return this.type_of_travel_document;
	}

	public void setType_of_travel_document(
			java.lang.String type_of_travel_document) {
		this.type_of_travel_document = type_of_travel_document;
	}

	public java.lang.String getTravel_document_number() {
		return this.travel_document_number;
	}

	public void setTravel_document_number(
			java.lang.String travel_document_number) {
		this.travel_document_number = travel_document_number;
	}

	public java.util.Date getTravel_document_issued_date() {
		return this.travel_document_issued_date;
	}

	public void setTravel_document_issued_date(
			java.util.Date travel_document_issued_date) {
		this.travel_document_issued_date = travel_document_issued_date;
	}

	public java.util.Date getTravel_document_expiry_date() {
		return this.travel_document_expiry_date;
	}

	public void setTravel_document_expiry_date(
			java.util.Date travel_document_expiry_date) {
		this.travel_document_expiry_date = travel_document_expiry_date;
	}

	public java.lang.String getCountry_of_issue() {
		return this.country_of_issue;
	}

	public void setCountry_of_issue(java.lang.String country_of_issue) {
		this.country_of_issue = country_of_issue;
	}

	public java.lang.String getPlace_of_issue() {
		return this.place_of_issue;
	}

	public void setPlace_of_issue(java.lang.String place_of_issue) {
		this.place_of_issue = place_of_issue;
	}

	public java.lang.String getCountry_of_origin() {
		return this.country_of_origin;
	}

	public void setCountry_of_origin(java.lang.String country_of_origin) {
		this.country_of_origin = country_of_origin;
	}

	public java.lang.String getPrefecture_of_origin() {
		return this.prefecture_of_origin;
	}

	public void setPrefecture_of_origin(java.lang.String prefecture_of_origin) {
		this.prefecture_of_origin = prefecture_of_origin;
	}

	public java.lang.String getHighest_academic() {
		return this.highest_academic;
	}

	public void setHighest_academic(java.lang.String highest_academic) {
		this.highest_academic = highest_academic;
	}

	public java.lang.String getReligion_denomination() {
		return this.religion_denomination;
	}

	public void setReligion_denomination(java.lang.String religion_denomination) {
		this.religion_denomination = religion_denomination;
	}

	public java.lang.String getPurpose_of_visit() {
		return this.purpose_of_visit;
	}

	public void setPurpose_of_visit(java.lang.String purpose_of_visit) {
		this.purpose_of_visit = purpose_of_visit;
	}

	public java.util.Date getIntended_date_of_arrival() {
		return this.intended_date_of_arrival;
	}

	public void setIntended_date_of_arrival(
			java.util.Date intended_date_of_arrival) {
		this.intended_date_of_arrival = intended_date_of_arrival;
	}

	public int getIntended_period_of_stay() {
		return this.intended_period_of_stay;
	}

	public void setIntended_period_of_stay(int intended_period_of_stay) {
		this.intended_period_of_stay = intended_period_of_stay;
	}

	public java.lang.String getNationality_of_spouse() {
		return this.nationality_of_spouse;
	}

	public void setNationality_of_spouse(java.lang.String nationality_of_spouse) {
		this.nationality_of_spouse = nationality_of_spouse;
	}

	public java.lang.String getNext_of_kin() {
		return this.next_of_kin;
	}

	public void setNext_of_kin(java.lang.String next_of_kin) {
		this.next_of_kin = next_of_kin;
	}

	public java.lang.String getRelative() {
		return this.relative;
	}

	public void setRelative(java.lang.String relative) {
		this.relative = relative;
	}

	public java.lang.String getFriend() {
		return this.friend;
	}

	public void setFriend(java.lang.String friend) {
		this.friend = friend;
	}

	public java.lang.String getHotel() {
		return this.hotel;
	}

	public void setHotel(java.lang.String hotel) {
		this.hotel = hotel;
	}

	public java.lang.String getOthers() {
		return this.others;
	}

	public void setOthers(java.lang.String others) {
		this.others = others;
	}

	public java.lang.String getBuilding_name() {
		return this.building_name;
	}

	public void setBuilding_name(java.lang.String building_name) {
		this.building_name = building_name;
	}

	public java.lang.String getBuilding_or_unit_number() {
		return this.building_or_unit_number;
	}

	public void setBuilding_or_unit_number(
			java.lang.String building_or_unit_number) {
		this.building_or_unit_number = building_or_unit_number;
	}

	public java.lang.String getFloor_number() {
		return this.floor_number;
	}

	public void setFloor_number(java.lang.String floor_number) {
		this.floor_number = floor_number;
	}

	public java.lang.String getRoad_street() {
		return this.road_street;
	}

	public void setRoad_street(java.lang.String road_street) {
		this.road_street = road_street;
	}

	public java.lang.String getReference_name() {
		return this.reference_name;
	}

	public void setReference_name(java.lang.String reference_name) {
		this.reference_name = reference_name;
	}

	public java.lang.String getReference_contact_number() {
		return this.reference_contact_number;
	}

	public void setReference_contact_number(
			java.lang.String reference_contact_number) {
		this.reference_contact_number = reference_contact_number;
	}

	public java.lang.String getNickname() {
		return this.nickname;
	}

	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}

	public java.lang.String getChildhood_name() {
		return this.childhood_name;
	}

	public void setChildhood_name(java.lang.String childhood_name) {
		this.childhood_name = childhood_name;
	}

	public java.lang.String getArtist_name() {
		return this.artist_name;
	}

	public void setArtist_name(java.lang.String artist_name) {
		this.artist_name = artist_name;
	}

	public java.lang.String getOther_name() {
		return this.other_name;
	}

	public void setOther_name(java.lang.String other_name) {
		this.other_name = other_name;
	}

	public java.lang.String getPhoto() {
		return this.photo;
	}

	public void setPhoto(java.lang.String photo) {
		this.photo = photo;
	}

	public java.lang.String getBlood_type() {
		return this.blood_type;
	}

	public void setBlood_type(java.lang.String blood_type) {
		this.blood_type = blood_type;
	}

	public java.lang.String getEye_colour() {
		return this.eye_colour;
	}

	public void setEye_colour(java.lang.String eye_colour) {
		this.eye_colour = eye_colour;
	}

	public java.lang.String getHeight() {
		return this.height;
	}

	public void setHeight(java.lang.String height) {
		this.height = height;
	}

	public java.lang.String getDistinct_feature() {
		return this.distinct_feature;
	}

	public void setDistinct_feature(java.lang.String distinct_feature) {
		this.distinct_feature = distinct_feature;
	}

	public java.lang.String getHair_colour() {
		return this.hair_colour;
	}

	public void setHair_colour(java.lang.String hair_colour) {
		this.hair_colour = hair_colour;
	}

	public java.lang.String getBody_weight() {
		return this.body_weight;
	}

	public void setBody_weight(java.lang.String body_weight) {
		this.body_weight = body_weight;
	}

	public java.lang.String getBorn_in() {
		return this.born_in;
	}

	public void setBorn_in(java.lang.String born_in) {
		this.born_in = born_in;
	}

	public java.lang.String getBreath_in() {
		return this.breath_in;
	}

	public void setBreath_in(java.lang.String breath_in) {
		this.breath_in = breath_in;
	}

	public java.lang.String getParents() {
		return this.parents;
	}

	public void setParents(java.lang.String parents) {
		this.parents = parents;
	}

	public java.lang.String getChildren() {
		return this.children;
	}

	public void setChildren(java.lang.String children) {
		this.children = children;
	}

	public java.lang.String getNrc_copy_front() {
		return this.nrc_copy_front;
	}

	public void setNrc_copy_front(java.lang.String nrc_copy_front) {
		this.nrc_copy_front = nrc_copy_front;
	}

	public java.lang.String getNrc_copy_back() {
		return this.nrc_copy_back;
	}

	public void setNrc_copy_back(java.lang.String nrc_copy_back) {
		this.nrc_copy_back = nrc_copy_back;
	}

	public java.lang.String getPassport_copy() {
		return this.passport_copy;
	}

	public void setPassport_copy(java.lang.String passport_copy) {
		this.passport_copy = passport_copy;
	}

	public java.lang.String getDriving_license_copy() {
		return this.driving_license_copy;
	}

	public void setDriving_license_copy(java.lang.String driving_license_copy) {
		this.driving_license_copy = driving_license_copy;
	}

	public java.lang.String getHouse_hold_list_copy_front() {
		return this.house_hold_list_copy_front;
	}

	public void setHouse_hold_list_copy_front(
			java.lang.String house_hold_list_copy_front) {
		this.house_hold_list_copy_front = house_hold_list_copy_front;
	}

	public java.lang.String getHouse_hold_list_copy_back() {
		return this.house_hold_list_copy_back;
	}

	public void setHouse_hold_list_copy_back(
			java.lang.String house_hold_list_copy_back) {
		this.house_hold_list_copy_back = house_hold_list_copy_back;
	}

	public java.lang.String getPolice_recommendation_letter_copy() {
		return this.police_recommendation_letter_copy;
	}

	public void setPolice_recommendation_letter_copy(
			java.lang.String police_recommendation_letter_copy) {
		this.police_recommendation_letter_copy = police_recommendation_letter_copy;
	}

	public java.lang.String getAdministrative_recommendation_letter_copy() {
		return this.administrative_recommendation_letter_copy;
	}

	public void setAdministrative_recommendation_letter_copy(
			java.lang.String administrative_recommendation_letter_copy) {
		this.administrative_recommendation_letter_copy = administrative_recommendation_letter_copy;
	}

	public java.lang.String getLabour_card_copy() {
		return this.labour_card_copy;
	}

	public void setLabour_card_copy(java.lang.String labour_card_copy) {
		this.labour_card_copy = labour_card_copy;
	}

	public java.lang.String getStaff_card_copy() {
		return this.staff_card_copy;
	}

	public void setStaff_card_copy(java.lang.String staff_card_copy) {
		this.staff_card_copy = staff_card_copy;
	}

	public java.lang.String getFamily_lawyer() {
		return this.family_lawyer;
	}

	public void setFamily_lawyer(java.lang.String family_lawyer) {
		this.family_lawyer = family_lawyer;
	}

	public java.lang.String getFamily_doctor() {
		return this.family_doctor;
	}

	public void setFamily_doctor(java.lang.String family_doctor) {
		this.family_doctor = family_doctor;
	}

	public java.lang.String getCustom1() {
		return this.custom1;
	}

	public void setCustom1(java.lang.String custom1) {
		this.custom1 = custom1;
	}

	public java.lang.String getCustom2() {
		return this.custom2;
	}

	public void setCustom2(java.lang.String custom2) {
		this.custom2 = custom2;
	}

	public java.lang.String getCustom3() {
		return this.custom3;
	}

	public void setCustom3(java.lang.String custom3) {
		this.custom3 = custom3;
	}

	public java.lang.String getCustom4() {
		return this.custom4;
	}

	public void setCustom4(java.lang.String custom4) {
		this.custom4 = custom4;
	}

	public java.lang.String getCustom5() {
		return this.custom5;
	}

	public void setCustom5(java.lang.String custom5) {
		this.custom5 = custom5;
	}

	public java.lang.String getCustom6() {
		return this.custom6;
	}

	public void setCustom6(java.lang.String custom6) {
		this.custom6 = custom6;
	}

	public java.lang.String getCustom7() {
		return this.custom7;
	}

	public void setCustom7(java.lang.String custom7) {
		this.custom7 = custom7;
	}

	public java.lang.String getCustom8() {
		return this.custom8;
	}

	public void setCustom8(java.lang.String custom8) {
		this.custom8 = custom8;
	}

	public java.lang.String getCustom9() {
		return this.custom9;
	}

	public void setCustom9(java.lang.String custom9) {
		this.custom9 = custom9;
	}

	public java.lang.String getCustom10() {
		return this.custom10;
	}

	public void setCustom10(java.lang.String custom10) {
		this.custom10 = custom10;
	}

	public java.lang.String getAttachment1() {
		return this.attachment1;
	}

	public void setAttachment1(java.lang.String attachment1) {
		this.attachment1 = attachment1;
	}

	public java.lang.String getAttachment2() {
		return this.attachment2;
	}

	public void setAttachment2(java.lang.String attachment2) {
		this.attachment2 = attachment2;
	}

	public java.lang.String getAttachment3() {
		return this.attachment3;
	}

	public void setAttachment3(java.lang.String attachment3) {
		this.attachment3 = attachment3;
	}

	public java.lang.String getAttachment4() {
		return this.attachment4;
	}

	public void setAttachment4(java.lang.String attachment4) {
		this.attachment4 = attachment4;
	}

	public java.lang.String getAttachment5() {
		return this.attachment5;
	}

	public void setAttachment5(java.lang.String attachment5) {
		this.attachment5 = attachment5;
	}

	public java.lang.String getAttachment6() {
		return this.attachment6;
	}

	public void setAttachment6(java.lang.String attachment6) {
		this.attachment6 = attachment6;
	}

	public java.lang.String getAttachment7() {
		return this.attachment7;
	}

	public void setAttachment7(java.lang.String attachment7) {
		this.attachment7 = attachment7;
	}

	public java.lang.String getAttachment8() {
		return this.attachment8;
	}

	public void setAttachment8(java.lang.String attachment8) {
		this.attachment8 = attachment8;
	}

	public java.lang.String getAttachment9() {
		return this.attachment9;
	}

	public void setAttachment9(java.lang.String attachment9) {
		this.attachment9 = attachment9;
	}

	public java.lang.String getAttachment10() {
		return this.attachment10;
	}

	public void setAttachment10(java.lang.String attachment10) {
		this.attachment10 = attachment10;
	}

	public java.util.Date getCustom_date1() {
		return this.custom_date1;
	}

	public void setCustom_date1(java.util.Date custom_date1) {
		this.custom_date1 = custom_date1;
	}

	public java.util.Date getCustom_date2() {
		return this.custom_date2;
	}

	public void setCustom_date2(java.util.Date custom_date2) {
		this.custom_date2 = custom_date2;
	}

	public java.util.Date getCustom_date3() {
		return this.custom_date3;
	}

	public void setCustom_date3(java.util.Date custom_date3) {
		this.custom_date3 = custom_date3;
	}

	public java.util.Date getCustom_date4() {
		return this.custom_date4;
	}

	public void setCustom_date4(java.util.Date custom_date4) {
		this.custom_date4 = custom_date4;
	}

	public java.util.Date getCustom_date5() {
		return this.custom_date5;
	}

	public void setCustom_date5(java.util.Date custom_date5) {
		this.custom_date5 = custom_date5;
	}

	public java.lang.String getState() {
		return this.state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

	public java.lang.String getCountry() {
		return this.country;
	}

	public void setCountry(java.lang.String country) {
		this.country = country;
	}

    public Customer(Long id, String customerId, String firstname, String middlename, String lastname, String nationality, java.util.Date dob, String gender, String maritalstatus, String nrc, String passport, String drivinglicense, String address, String address2, String city, String state, String country, String postalcode, String homephone, String mobilephone, String workphone, String workemail, String privateemail, String professions, String ethnicity, java.util.Date created, java.util.Date updated, int status, Long department, String title, String otherId, java.util.Date driving_license_exp_date, String occupation, String workaddress, String immigration_status, String notes, String ward, String township, String village, java.util.Date country_of_birth, String state_province_of_birth, String race, String other_race, String type_of_travel_document, String travel_document_number, java.util.Date travel_document_issued_date, java.util.Date travel_document_expiry_date, String country_of_issue, String place_of_issue, String country_of_origin, String prefecture_of_origin, String highest_academic, String religion_denomination, String purpose_of_visit, java.util.Date intended_date_of_arrival, int intended_period_of_stay, String nationality_of_spouse, String next_of_kin, String relative, String friend, String hotel, String others, String building_name, String building_or_unit_number, String floor_number, String road_street, String reference_name, String reference_contact_number, String nickname, String childhood_name, String artist_name, String other_name, String photo, String blood_type, String eye_colour, String height, String distinct_feature, String hair_colour, String body_weight, String born_in, String breath_in, String parents, String children, String nrc_copy_front, String nrc_copy_back, String passport_copy, String driving_license_copy, String house_hold_list_copy_front, String house_hold_list_copy_back, String police_recommendation_letter_copy, String administrative_recommendation_letter_copy, String labour_card_copy, String staff_card_copy, String family_lawyer, String family_doctor, String custom1, String custom2, String custom3, String custom4, String custom5, String custom6, String custom7, String custom8, String custom9, String custom10, String attachment1, String attachment2, String attachment3, String attachment4, String attachment5, String attachment6, String attachment7, String attachment8, String attachment9, String attachment10, java.util.Date custom_date1, java.util.Date custom_date2, java.util.Date custom_date3, java.util.Date custom_date4, java.util.Date custom_date5) {
        this.id = id;
        this.customerId = customerId;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nationality = nationality;
        this.dob = dob;
        this.gender = gender;
        this.maritalstatus = maritalstatus;
        this.nrc = nrc;
        this.passport = passport;
        this.drivinglicense = drivinglicense;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalcode = postalcode;
        this.homephone = homephone;
        this.mobilephone = mobilephone;
        this.workphone = workphone;
        this.workemail = workemail;
        this.privateemail = privateemail;
        this.professions = professions;
        this.ethnicity = ethnicity;
        this.created = created;
        this.updated = updated;
        this.status = status;
        this.department = department;
        this.title = title;
        this.otherId = otherId;
        this.driving_license_exp_date = driving_license_exp_date;
        this.occupation = occupation;
        this.workaddress = workaddress;
        this.immigration_status = immigration_status;
        this.notes = notes;
        this.ward = ward;
        this.township = township;
        this.village = village;
        this.country_of_birth = country_of_birth;
        this.state_province_of_birth = state_province_of_birth;
        this.race = race;
        this.other_race = other_race;
        this.type_of_travel_document = type_of_travel_document;
        this.travel_document_number = travel_document_number;
        this.travel_document_issued_date = travel_document_issued_date;
        this.travel_document_expiry_date = travel_document_expiry_date;
        this.country_of_issue = country_of_issue;
        this.place_of_issue = place_of_issue;
        this.country_of_origin = country_of_origin;
        this.prefecture_of_origin = prefecture_of_origin;
        this.highest_academic = highest_academic;
        this.religion_denomination = religion_denomination;
        this.purpose_of_visit = purpose_of_visit;
        this.intended_date_of_arrival = intended_date_of_arrival;
        this.intended_period_of_stay = intended_period_of_stay;
        this.nationality_of_spouse = nationality_of_spouse;
        this.next_of_kin = next_of_kin;
        this.relative = relative;
        this.friend = friend;
        this.hotel = hotel;
        this.others = others;
        this.building_name = building_name;
        this.building_or_unit_number = building_or_unit_number;
        this.floor_number = floor_number;
        this.road_street = road_street;
        this.reference_name = reference_name;
        this.reference_contact_number = reference_contact_number;
        this.nickname = nickname;
        this.childhood_name = childhood_name;
        this.artist_name = artist_name;
        this.other_name = other_name;
        this.photo = photo;
        this.blood_type = blood_type;
        this.eye_colour = eye_colour;
        this.height = height;
        this.distinct_feature = distinct_feature;
        this.hair_colour = hair_colour;
        this.body_weight = body_weight;
        this.born_in = born_in;
        this.breath_in = breath_in;
        this.parents = parents;
        this.children = children;
        this.nrc_copy_front = nrc_copy_front;
        this.nrc_copy_back = nrc_copy_back;
        this.passport_copy = passport_copy;
        this.driving_license_copy = driving_license_copy;
        this.house_hold_list_copy_front = house_hold_list_copy_front;
        this.house_hold_list_copy_back = house_hold_list_copy_back;
        this.police_recommendation_letter_copy = police_recommendation_letter_copy;
        this.administrative_recommendation_letter_copy = administrative_recommendation_letter_copy;
        this.labour_card_copy = labour_card_copy;
        this.staff_card_copy = staff_card_copy;
        this.family_lawyer = family_lawyer;
        this.family_doctor = family_doctor;
        this.custom1 = custom1;
        this.custom2 = custom2;
        this.custom3 = custom3;
        this.custom4 = custom4;
        this.custom5 = custom5;
        this.custom6 = custom6;
        this.custom7 = custom7;
        this.custom8 = custom8;
        this.custom9 = custom9;
        this.custom10 = custom10;
        this.attachment1 = attachment1;
        this.attachment2 = attachment2;
        this.attachment3 = attachment3;
        this.attachment4 = attachment4;
        this.attachment5 = attachment5;
        this.attachment6 = attachment6;
        this.attachment7 = attachment7;
        this.attachment8 = attachment8;
        this.attachment9 = attachment9;
        this.attachment10 = attachment10;
        this.custom_date1 = custom_date1;
        this.custom_date2 = custom_date2;
        this.custom_date3 = custom_date3;
        this.custom_date4 = custom_date4;
        this.custom_date5 = custom_date5;
    }

    public Customer(String customerId, String firstname, String middlename, String lastname, String nationality, java.util.Date dob, String gender, String maritalstatus, String nrc, String passport, String drivinglicense, String address, String address2, String city, String state, String country, String postalcode, String homephone, String mobilephone, String workphone, String workemail, String privateemail, String professions, String ethnicity, java.util.Date created, java.util.Date updated, int status, Long department, String title, String otherId, java.util.Date driving_license_exp_date, String occupation, String workaddress, String immigration_status, String notes, String ward, String township, String village, java.util.Date country_of_birth, String state_province_of_birth, String race, String other_race, String type_of_travel_document, String travel_document_number, java.util.Date travel_document_issued_date, java.util.Date travel_document_expiry_date, String country_of_issue, String place_of_issue, String country_of_origin, String prefecture_of_origin, String highest_academic, String religion_denomination, String purpose_of_visit, java.util.Date intended_date_of_arrival, int intended_period_of_stay, String nationality_of_spouse, String next_of_kin, String relative, String friend, String hotel, String others, String building_name, String building_or_unit_number, String floor_number, String road_street, String reference_name, String reference_contact_number, String nickname, String childhood_name, String artist_name, String other_name, String photo, String blood_type, String eye_colour, String height, String distinct_feature, String hair_colour, String body_weight, String born_in, String breath_in, String parents, String children, String nrc_copy_front, String nrc_copy_back, String passport_copy, String driving_license_copy, String house_hold_list_copy_front, String house_hold_list_copy_back, String police_recommendation_letter_copy, String administrative_recommendation_letter_copy, String labour_card_copy, String staff_card_copy, String family_lawyer, String family_doctor, String custom1, String custom2, String custom3, String custom4, String custom5, String custom6, String custom7, String custom8, String custom9, String custom10, String attachment1, String attachment2, String attachment3, String attachment4, String attachment5, String attachment6, String attachment7, String attachment8, String attachment9, String attachment10, java.util.Date custom_date1, java.util.Date custom_date2, java.util.Date custom_date3, java.util.Date custom_date4, java.util.Date custom_date5) {
        this.customerId = customerId;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nationality = nationality;
        this.dob = dob;
        this.gender = gender;
        this.maritalstatus = maritalstatus;
        this.nrc = nrc;
        this.passport = passport;
        this.drivinglicense = drivinglicense;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalcode = postalcode;
        this.homephone = homephone;
        this.mobilephone = mobilephone;
        this.workphone = workphone;
        this.workemail = workemail;
        this.privateemail = privateemail;
        this.professions = professions;
        this.ethnicity = ethnicity;
        this.created = created;
        this.updated = updated;
        this.status = status;
        this.department = department;
        this.title = title;
        this.otherId = otherId;
        this.driving_license_exp_date = driving_license_exp_date;
        this.occupation = occupation;
        this.workaddress = workaddress;
        this.immigration_status = immigration_status;
        this.notes = notes;
        this.ward = ward;
        this.township = township;
        this.village = village;
        this.country_of_birth = country_of_birth;
        this.state_province_of_birth = state_province_of_birth;
        this.race = race;
        this.other_race = other_race;
        this.type_of_travel_document = type_of_travel_document;
        this.travel_document_number = travel_document_number;
        this.travel_document_issued_date = travel_document_issued_date;
        this.travel_document_expiry_date = travel_document_expiry_date;
        this.country_of_issue = country_of_issue;
        this.place_of_issue = place_of_issue;
        this.country_of_origin = country_of_origin;
        this.prefecture_of_origin = prefecture_of_origin;
        this.highest_academic = highest_academic;
        this.religion_denomination = religion_denomination;
        this.purpose_of_visit = purpose_of_visit;
        this.intended_date_of_arrival = intended_date_of_arrival;
        this.intended_period_of_stay = intended_period_of_stay;
        this.nationality_of_spouse = nationality_of_spouse;
        this.next_of_kin = next_of_kin;
        this.relative = relative;
        this.friend = friend;
        this.hotel = hotel;
        this.others = others;
        this.building_name = building_name;
        this.building_or_unit_number = building_or_unit_number;
        this.floor_number = floor_number;
        this.road_street = road_street;
        this.reference_name = reference_name;
        this.reference_contact_number = reference_contact_number;
        this.nickname = nickname;
        this.childhood_name = childhood_name;
        this.artist_name = artist_name;
        this.other_name = other_name;
        this.photo = photo;
        this.blood_type = blood_type;
        this.eye_colour = eye_colour;
        this.height = height;
        this.distinct_feature = distinct_feature;
        this.hair_colour = hair_colour;
        this.body_weight = body_weight;
        this.born_in = born_in;
        this.breath_in = breath_in;
        this.parents = parents;
        this.children = children;
        this.nrc_copy_front = nrc_copy_front;
        this.nrc_copy_back = nrc_copy_back;
        this.passport_copy = passport_copy;
        this.driving_license_copy = driving_license_copy;
        this.house_hold_list_copy_front = house_hold_list_copy_front;
        this.house_hold_list_copy_back = house_hold_list_copy_back;
        this.police_recommendation_letter_copy = police_recommendation_letter_copy;
        this.administrative_recommendation_letter_copy = administrative_recommendation_letter_copy;
        this.labour_card_copy = labour_card_copy;
        this.staff_card_copy = staff_card_copy;
        this.family_lawyer = family_lawyer;
        this.family_doctor = family_doctor;
        this.custom1 = custom1;
        this.custom2 = custom2;
        this.custom3 = custom3;
        this.custom4 = custom4;
        this.custom5 = custom5;
        this.custom6 = custom6;
        this.custom7 = custom7;
        this.custom8 = custom8;
        this.custom9 = custom9;
        this.custom10 = custom10;
        this.attachment1 = attachment1;
        this.attachment2 = attachment2;
        this.attachment3 = attachment3;
        this.attachment4 = attachment4;
        this.attachment5 = attachment5;
        this.attachment6 = attachment6;
        this.attachment7 = attachment7;
        this.attachment8 = attachment8;
        this.attachment9 = attachment9;
        this.attachment10 = attachment10;
        this.custom_date1 = custom_date1;
        this.custom_date2 = custom_date2;
        this.custom_date3 = custom_date3;
        this.custom_date4 = custom_date4;
        this.custom_date5 = custom_date5;
    }

        
        
}
