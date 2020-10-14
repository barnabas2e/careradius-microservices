import { Gender } from './Gender';

export class Member {

    internalId: number;
    memberId: string;
    firstName: string;
    lastName: string;
    formattedName: string;
    gender: Gender;
    address: string;
}
