export class UserModel {

    constructor(
    public role?: string,
    public items?: string,
    public orders?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public username: string = ""
    ) {}
}