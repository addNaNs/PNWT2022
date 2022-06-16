function validateToken(token: string) {
  return token;
}

export class Globals {
  public static user: {name: string, token: string} = {
    name : localStorage.getItem("name") ? validateToken(localStorage.getItem("name") as string) : "",
    token : localStorage.getItem("token") ? validateToken(localStorage.getItem("token") as string) : "",
  };
    
  public static cart: string[] = JSON.parse(localStorage.getItem("cart") ? validateToken(localStorage.getItem("cart") as string) : '[]');

  public static token : string = '';
}