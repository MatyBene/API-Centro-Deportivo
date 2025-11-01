import SportActivitySummary from "./SportActivitySummary";

export default interface Instructor {
    id: number,
    name: string,
    lastname: string,
    speciality: string,
    birthdate: string,
    activities: SportActivitySummary[],
    role: string,
    username: string,
    password: string
}