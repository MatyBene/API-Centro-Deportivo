export default interface SportActivity {
    name: string,
    maxMembers: number,
    instructorName: string,
    description: string,
    currentMembers: number,
    startTime: string,
    endTime: string,
    classDays: string[];
}