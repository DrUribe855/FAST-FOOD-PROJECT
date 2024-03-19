<template>
    <div>
        <v-app>
            <v-main>
                <template>
                    <v-form v-model="valid">
                        <v-container>
                            <v-row>
                                <v-col cols="12" md="4">
                                    <v-text-field
                                        v-model="Nombres"
                                        :rules="nameRules"
                                        :counter="10"
                                        label="Nombres"
                                        required
                                    ></v-text-field>
                                </v-col>

                                <v-col cols="12" md="4" >
                                    <v-text-field
                                        v-model="Apellidos"
                                        :rules="nameRules"
                                        :counter="10"
                                        label="Apellidos"
                                        required
                                    ></v-text-field>
                                </v-col>

                                <v-col cols="12" md="4" >
                                    <v-text-field
                                        v-model="email"
                                        :rules="emailRules"
                                        label="E-mail"
                                        required
                                    ></v-text-field>
                                    
                                </v-col>

                                <v-col cols="12" md="4" >
                                    <v-btn depressed color="primary" >
                                    Enviar
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-form>
                </template>
            </v-main>
        </v-app>
    </div>
</template>
<script>
  export default {
    data: () => ({
      valid: false,
      Nombres: '',
      Apellidos: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 10 || 'Name must be less than 10 characters',
      ],
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid',
      ],
    }),
  }
</script>