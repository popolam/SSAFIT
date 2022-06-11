<template>
  <v-container fluid class="pa-0 ma-0">
    <v-row>
			<!-- Login Form -->
			<v-col justify-center cols="12" md="6" lg="6" xl="6" style="padding-top: 25vh; height: 1000px" class="blur d-flex justify-center">
				<v-card style="height: 320px; min-width: 30vw;">
					<v-card-text>
						<v-container ref="form">
							<v-row>
								<v-col>
									<v-text-field
										ref="email"
										v-model="email"
										:rules="[
											() => !!email || '이메일을 입력해 주세요',
											emailcheck
										]"
										label="이메일"
										placeholder="ssafy@ssafy.com"
										clearable
										clear-icon="cancel"
										required
										@keydown.enter="submit"
									></v-text-field>
								</v-col>
							</v-row>
							<v-row>
								<v-col>
									<v-text-field
										ref="password"
										v-model="password"
										:append-icon="showPassword ? 'visibility' : 'visibility_off'"
										:rules="[
											() => !!password || '비밀번호를 입력해 주세요',
											() => password && password.length >= 4 || '비밀번호는 4자 이상입니다.',
										]"
										:type="showPassword ? 'text' : 'password'"
										label="비밀번호"
										counter
										clearable
										clear-icon="cancel"
										@keydown.enter="submit"
										@click:append="showPassword = !showPassword"
									></v-text-field>
								</v-col>
							</v-row>
						</v-container>
					</v-card-text>
					<v-divider class="mt-12"></v-divider>
					<v-card-actions>
						<v-btn text @click="joinForm">Join</v-btn>
						<v-spacer></v-spacer>
						<v-btn text @click="submit" color="primary">Submit</v-btn>
					</v-card-actions>
				</v-card>
			</v-col>
			<!-- Image -->
			<v-col style="height: 1000px background: none">
			</v-col>
		</v-row>

	</v-container>
</template>

<script>
  export default {
    data: () => ({
        email: null,
        password: null,
        showPassword: false,
        emailcheck: e => /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(e) || '올바른 이메일 주소를 입력해 주세요',
    }),
    computed: {
      form() {
        return [
            'email',
            'password'
        ]
      },
    },
    methods: {
      joinForm() {
        this.$router.push('join');
      },
      submit() {
        let flag = true;
        this.form.forEach(f => {
            if(!this.$refs[f].validate(true)){
                flag = false;
            }
        })
        if(flag){
            this.$store.dispatch("userLogin", {
            user: { userId: this.email, password: this.password },
            call: this.$route.query.call,
          });
        }
      },
      logout() {
        alert("logout");
      },
    },
  }
</script>

<style>
	.blur{
    -webkit-backdrop-filter: blur(50px);
    backdrop-filter: blur(50px);
    background-blend-mode: overlay;
	}
</style>