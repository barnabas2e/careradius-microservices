import { ContactMethod } from './contactMethod';

export class Authorization {

    internalId: number;
    referenceNumber: string;
    dateInitiated: any;
    dateValidFrom: any;
    dateValidTo: any;
    // contact info
    contactName: string;
    contactPhone: string;
    contactMethod: ContactMethod;
}
