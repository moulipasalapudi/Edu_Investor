export class User {
    userId?: number;
    email: string;
    password: string;
    username: string;
    mobileNumber: string;
    userRole: string;
  
    constructor(
      userId: number | undefined,
      email: string,
      password: string,
      username: string,
      mobileNumber: string,
      userRole: string
    ) {
      this.userId = userId;
      this.email = email;
      this.password = password;
      this.username = username;
      this.mobileNumber = mobileNumber;
      this.userRole = userRole;
    }
  }
  