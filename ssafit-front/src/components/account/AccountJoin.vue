<template>
  <v-container fluid class="pa-0 ma-0">
    <v-row>
			<!-- Login Form -->
			<v-col justify-center cols="12" md="6" lg="6" xl="6" style="padding-top: 10vh; height: 1000px" class="blur d-flex justify-center">
				<v-card style="height: 600px;">
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
											() => password && password.length >= 4 || '비밀번호는 4자 이상으로 해야합니다.',
										]"
										:type="showPassword ? 'text' : 'password'"
										label="비밀번호"
										hint="비밀번호는 4자 이상으로 해야합니다."
										counter
										clearable
										clear-icon="cancel"
										@click:append="showPassword = !showPassword"
									></v-text-field>
								</v-col>
							</v-row>
							<v-row>
								<v-col>
									<v-text-field
										ref="name"
										v-model="name"
										:rules="[
											() => !!name || '이름을 입력해 주세요',
											() => name && name.length <= 8 || '이름은 8글자 제한입니다.',
										]"
										label="이름"
										placeholder="홍길동"
										clearable
										clear-icon="cancel"
										required
									></v-text-field>
								</v-col>
							</v-row>
							<v-row>
								<v-col>
									<v-text-field
										ref="studentId"
										v-model="studentId"
										:rules="[
												() => !!studentId || '학번을 입력해 주세요',
												studentIdCheck
										]"
										label="학번"
										placeholder="1234567"
										clearable
										clear-icon="cancel"
										required
									></v-text-field>
								</v-col>
							</v-row>
							<v-row>
								<v-col>
									<v-autocomplete
										ref="area"
										v-model="area"
										:rules="[() => !!area || '지역을 입력해 주세요']"
										:items="Object.keys(classData)"
										label="지역"
										placeholder="지역 선택..."
										required
									></v-autocomplete>
								</v-col>
								<v-col>
									<v-autocomplete
										ref="className"
										v-model="className"
										:rules="[() => !!className || '반을 입력해 주세요']"
										:items="classes"
										label="반"
										placeholder="반 선택..."
										required
									></v-autocomplete>
								</v-col>
							</v-row>
						</v-container>
					</v-card-text>
					<v-divider class="mt-12"></v-divider>
					<v-card-actions>
						<v-btn text @click="loginForm">Login</v-btn>
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
        name: null,
        studentId: null,
        area: null,
        className: null,
        showPassword: false,
        classData: {
            서울: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21],
            대전: [1, 2, 3, 4, 5, 6, 7, 8],
            광주: [1, 2, 3, 4, 5, 6],
            구미: [1, 2, 3, 4, 5, 6],
            부울경: [1, 2, 3, 4]
        },
        emailcheck: e => /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(e) || '올바른 이메일 주소를 입력해 주세요.',
        studentIdCheck: e => /^[0-9]{7}$/.test(e) || '올바르지 않은 학번입니다.',
    }),
    computed: {
      classes() {
          return this.classData[this.area]
      },
      form() {
        return [
          'email',
          'password',
          'name',
          'studentId',
          'area',
          'className'
        ]
      },
    },
    methods: {
      loginForm() {
        this.$router.push('login');
      },
      submit() {
        let flag = true;
        this.form.forEach(f => {
            if(!this.$refs[f].validate(true)){
                flag = false;
            }
        })
        if(flag){
          const payload = {
            userId: this.email,
            password: this.password,
            name: this.name,
            nickname: this.studentId,
            areaName: this.area,
            classNum: this.className
          };
          this.$store.dispatch("userJoin", payload);
        }
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