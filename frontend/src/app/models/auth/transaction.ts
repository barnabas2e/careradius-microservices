import { Authorization } from './authorization';

export class Transaction {

    internalId: number;
    authorization: Authorization;
    memberInternalId: number;
    createdBy: number; // user created by id
}
