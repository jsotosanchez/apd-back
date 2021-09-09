# School project - Spring Boot API
The following project was made for school, simulating a hospital system with 3 types of users.

- Doctors: Could manage their schedule as they pleased, adding and removing slots for patients to make appointments.
- Patients: Could schedule/cancel appointments.
- Admin: Had the ability to manage anyone's schedule.

It's a Spring API structured following Domain-Driven Design, with multiple CRUD operations and integrated with Twilio for notifications. 

## Highlights:
Email and SMS Notifications:

Integrated Mailgun and Twilio to send notifications when a Admin user modified a schedule or canceled an appointment.

## Disclaimer:
The scope of the project did not cover security, therefore I did not bother to secure users passwords using an encrypting library since it would have not had any impact on my final grade. In a real project I would not pass around an user's password carelessly
