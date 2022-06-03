#include <stdio.h>
#include <windows.h>

int main(void) {
	STARTUPINFO si;
	PROCESS_INFORMATION pi;

	ZeroMemory(&si, sizeof(si));
	si.cb = sizeof(si);
	ZeroMemory(&pi, sizeof(pi));

	TCHAR path[128] = TEXT("C:\\WINDOWS\\system32\\mspaint.exe");

	if (!CreateProcess(NULL, path, NULL,
		NULL,
		FALSE,
		0,
		NULL,
		NULL,
		&si, &pi))
	{
		fprintf(stderr, "Create Process Failed");
		return -1;
	}

	WaitForSingleObject(pi.hProcess, INFINITE);
	printf("Child Complete");

	CloseHandle(pi.hProcess);
	CloseHandle(pi.hThread);
}